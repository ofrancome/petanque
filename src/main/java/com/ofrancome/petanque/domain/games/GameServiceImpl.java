package com.ofrancome.petanque.domain.games;

import com.ofrancome.petanque.domain.LocalDateService;
import com.ofrancome.petanque.domain.api.GameService;
import com.ofrancome.petanque.domain.elo.EloCalculator;
import com.ofrancome.petanque.domain.exceptions.PlayerDoesNotExistException;
import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.Ranking;
import com.ofrancome.petanque.domain.seasons.Season;
import com.ofrancome.petanque.infra.GameRepository;
import com.ofrancome.petanque.infra.PlayerRepository;
import com.ofrancome.petanque.infra.RankingRepository;
import com.ofrancome.petanque.infra.SeasonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final SeasonRepository seasonRepository;
    private final EloCalculator eloCalculator;
    private final LocalDateService localDateService;
    private final RankingRepository rankingRepository;


    public GameServiceImpl(final PlayerRepository playerRepository, final GameRepository gameRepository, final SeasonRepository seasonRepository, final EloCalculator eloCalculator, final LocalDateService localDateService, final LocalDateService localDateService1, final RankingRepository rankingRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.seasonRepository = seasonRepository;
        this.eloCalculator = eloCalculator;
        this.localDateService = localDateService1;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Game addGame(final Set<String> winnersNames, final Set<String> losersName, final Integer losersScore) {
        log.info("Adding game");
        final Set<Player> winners = retrievePlayers(winnersNames);
        final Set<Player> losers = retrievePlayers(losersName);
        final Season currentSeason = seasonRepository.currentSeason();
        addRankingIfMissing(winners, currentSeason);
        addRankingIfMissing(losers, currentSeason);

        final Integer eloSwitch = eloCalculator.getEloSwitch(winners, losers, currentSeason);

        final Game newGame = new Game();
        newGame.setGameDay(localDateService.today());
        newGame.setLosersScore(losersScore);
        newGame.setEloSwitch(eloSwitch);
        final Game game = gameRepository.save(newGame);
        currentSeason.addGame(game);
        winners.forEach(p -> {
            p.addWin(game);
            playerRepository.save(p);
        });
        losers.forEach(p -> {
            p.addLoss(game);
            playerRepository.save(p);
        });
        log.info("Finished adding game");
        return game;
    }

    private void addRankingIfMissing(final Set<Player> losers, final Season currentSeason) {
        for (final Player loser : losers) {
            if (!loser.lastRanking().getSeason().equals(currentSeason)) {
                final Ranking ranking = new Ranking();
                ranking.setElo(1200);
                rankingRepository.save(ranking);
                currentSeason.addRanking(ranking);
                loser.addRanking(ranking);
            }
        }
    }

    @Override
    public Set<Game> retrieveGames() {
        log.info("Retrieving games");
        final Iterable<Game> gameEntities = gameRepository.findAll();
        final Set<Game> games = new HashSet<>();
        gameEntities.forEach(games::add);
        return games;
    }

    @Override
    public void deleteLastGame() {
        final Game lastGame = gameRepository.findLastGame();

        lastGame.getLosers().forEach(player ->
        {
            player.addElo(lastGame.getSeason(), lastGame.getEloSwitch());
            playerRepository.save(player);
        });
        lastGame.getWinners().forEach(player -> {
            player.deduceElo(lastGame.getSeason(), lastGame.getEloSwitch());
            playerRepository.save(player);
        });

        gameRepository.delete(lastGame);
    }

    private Set<Player> retrievePlayers(final Set<String> winners) {
        return winners.stream()
                .map(name -> {
                    final Optional<Player> player = playerRepository.findByName(name);
                    if (player.isEmpty()) throw new PlayerDoesNotExistException(name + " does not exist");
                    return player;
                })
                .map(Optional::get).collect(Collectors.toSet());
    }
}

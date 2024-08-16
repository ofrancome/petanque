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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final SeasonRepository seasonRepository;
    private final EloCalculator eloCalculator;
    private final LocalDateService localDateService;
    private final RankingRepository rankingRepository;


    public GameServiceImpl(PlayerRepository playerRepository, GameRepository gameRepository, SeasonRepository seasonRepository, EloCalculator eloCalculator, LocalDateService localDateService, LocalDateService localDateService1, RankingRepository rankingRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.seasonRepository = seasonRepository;
        this.eloCalculator = eloCalculator;
        this.localDateService = localDateService1;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Game addGame(Set<String> winnersNames, Set<String> losersName, Integer losersScore) {
        Set<Player> winners = retrievePlayers(winnersNames);
        Set<Player> losers = retrievePlayers(losersName);
        Season currentSeason = seasonRepository.currentSeason();
        addRankingIfMissing(winners, currentSeason);
        addRankingIfMissing(losers, currentSeason);

        Integer eloSwitch = eloCalculator.getEloSwitch(winners, losers, currentSeason);

        final Game newGame = new Game();
        newGame.setGameDay(localDateService.today());
        newGame.setLosersScore(losersScore);
        newGame.setEloSwitch(eloSwitch);
        Game game = gameRepository.save(newGame);
        currentSeason.addGame(game);
        winners.forEach(p -> {
            p.addWin(game);
            playerRepository.save(p);
        });
        losers.forEach(p -> {
            p.addLoss(game);
            playerRepository.save(p);
        });
        return game;
    }

    private void addRankingIfMissing(Set<Player> losers, Season currentSeason) {
        for (Player loser : losers) {
            if (!loser.lastRanking().getSeason().equals(currentSeason)) {
                Ranking ranking = new Ranking();
                ranking.setElo(1200);
                rankingRepository.save(ranking);
                currentSeason.addRanking(ranking);
                loser.addRanking(ranking);
            }
        }
    }

    @Override
    public Set<Game> retrieveGames() {
        Iterable<Game> gameEntities = gameRepository.findAll();
        Set<Game> games = new HashSet<>();
        gameEntities.forEach(games::add);
        return games;
    }

    private Set<Player> retrievePlayers(Set<String> winners) {
        return winners.stream()
                .map(name -> {
                    Optional<Player> player = playerRepository.findByName(name);
                    if (player.isEmpty()) throw new PlayerDoesNotExistException(name + " does not exist");
                    return player;
                })
                .map(Optional::get).collect(Collectors.toSet());
    }
}

package com.ofrancome.petanque.domain.players;

import com.ofrancome.petanque.domain.seasons.Season;
import com.ofrancome.petanque.infra.PlayerRepository;
import com.ofrancome.petanque.infra.RankingRepository;
import com.ofrancome.petanque.infra.SeasonRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PlayerServiceImpl implements PlayerService {

    public static final int STARTING_ELO = 1200;
    private final PlayerRepository playerRepository;
    private final SeasonRepository seasonRepository;
    private final RankingRepository rankingRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, SeasonRepository seasonRepository, RankingRepository rankingRepository) {
        this.playerRepository = playerRepository;
        this.seasonRepository = seasonRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Player addPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        player.setGamesLost(new HashSet<>());
        player.setGamesWon(new HashSet<>());
        player.setRankings(new HashSet<>());
        Season season = seasonRepository.currentSeason();
        Ranking ranking = new Ranking();
        ranking.setElo(STARTING_ELO);
        rankingRepository.save(ranking);
        season.addRanking(ranking);
        player.addRanking(ranking);

        return playerRepository.save(player);
    }

    @Override
    public Set<Player> retrievePlayers() {
        Iterable<Player> playerEntities = playerRepository.findAll();
        Set<Player> players = new HashSet<>();
        playerEntities.forEach(players::add);
        return players;
    }


}

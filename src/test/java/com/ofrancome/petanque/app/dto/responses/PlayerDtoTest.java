package com.ofrancome.petanque.app.dto.responses;

import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.Ranking;
import com.ofrancome.petanque.domain.seasons.Season;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDtoTest {

    @Test
    @DisplayName("Should correctly convert from Player to PlayerDto")
    public void shouldCorrectlyConvertFromPlayerToPlayerDto() {
        PlayerDto expected = new PlayerDto(
                "olivier",
                "maboi.png",
                new PlayerRankingDto(1L, 1216),
                List.of(new PlayerRankingDto(1L, 1216)),
                1,
                1,
                100,
                1L,
                1,
                1,
                100
        );
        Game game = new Game();
        game.setId(1L);
        game.setGameDay(LocalDate.of(2024, Month.APRIL, 11));
        game.setLosersScore(4);
        game.setEloSwitch(16);
        Season season = new Season();
        season.setId(1L);
        Player olivier = new Player();
        olivier.setId(1L);
        olivier.setName("olivier");
        olivier.setAvatar("maboi.png");
        olivier.setGamesWon(new HashSet<>());
        olivier.setGamesLost(new HashSet<>());
        Ranking olivierRanking = new Ranking();
        olivierRanking.setId(1L);
        olivierRanking.setElo(1200);
        olivier.addRanking(olivierRanking);
        season.addRanking(olivierRanking);
        Player pes = new Player();
        pes.setId(2L);
        pes.setName("PES");
        Ranking pesRanking = new Ranking();
        pesRanking.setId(2L);
        pesRanking.setElo(1200);
        pes.addRanking(pesRanking);
        season.addRanking(pesRanking);
        Player jon = new Player();
        jon.setId(3L);
        jon.setName("jon");
        Ranking jonRanking = new Ranking();
        jonRanking.setId(3L);
        jonRanking.setElo(1200);
        jon.addRanking(jonRanking);
        season.addRanking(jonRanking);
        Player tim = new Player();
        tim.setId(4L);
        tim.setName("tim");
        Ranking timRanking = new Ranking();
        timRanking.setId(4L);
        timRanking.setElo(1200);
        tim.addRanking(timRanking);
        season.addRanking(timRanking);
        season.addGame(game);
        olivier.addWin(game);
        pes.addWin(game);
        jon.addLoss(game);
        tim.addLoss(game);

        Assertions.assertThat(PlayerDto.from(olivier)).isEqualTo(expected);
    }

}
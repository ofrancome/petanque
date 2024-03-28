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

class SeasonDtoTest {

    @Test
    @DisplayName("Should correctly convert from Season to SeasonDto")
    public void shouldCorrectlyConvertFromSeasonToSeasonDto() {
        SeasonDto expected = new SeasonDto(1L, "2024-04-11", "2024-04-20", Arrays.asList(new SeasonRankingDto("jon", 1184), new SeasonRankingDto("olivier", 1216)));
        Game game = new Game();
        game.setId(1L);
        game.setGameDay(LocalDate.of(2024, Month.APRIL, 11));
        game.setLosersScore(4);
        game.setEloSwitch(16);
        Season season = new Season();
        season.setId(1L);
        season.setStart(LocalDate.of(2024, Month.APRIL, 11));
        season.setEnd(LocalDate.of(2024, Month.APRIL, 20));
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
        Player jon = new Player();
        jon.setId(3L);
        jon.setName("jon");
        Ranking jonRanking = new Ranking();
        jonRanking.setId(3L);
        jonRanking.setElo(1200);
        jon.addRanking(jonRanking);
        season.addRanking(jonRanking);
        season.addGame(game);
        olivier.addWin(game);
        jon.addLoss(game);

        Assertions.assertThat(SeasonDto.from(season)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should correctly convert from Season to SeasonDto when endDate is null")
    public void shouldCorrectlyConvertFromSeasonToSeasonDtoWhenEndDateisNull() {
        SeasonDto expected = new SeasonDto(1L, "2024-04-11", null, Arrays.asList(new SeasonRankingDto("jon", 1184), new SeasonRankingDto("olivier", 1216)));
        Game game = new Game();
        game.setId(1L);
        game.setGameDay(LocalDate.of(2024, Month.APRIL, 11));
        game.setLosersScore(4);
        game.setEloSwitch(16);
        Season season = new Season();
        season.setId(1L);
        season.setStart(LocalDate.of(2024, Month.APRIL, 11));
        season.setEnd(null);
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
        Player jon = new Player();
        jon.setId(3L);
        jon.setName("jon");
        Ranking jonRanking = new Ranking();
        jonRanking.setId(3L);
        jonRanking.setElo(1200);
        jon.addRanking(jonRanking);
        season.addRanking(jonRanking);
        season.addGame(game);
        olivier.addWin(game);
        jon.addLoss(game);

        Assertions.assertThat(SeasonDto.from(season)).isEqualTo(expected);
    }
}
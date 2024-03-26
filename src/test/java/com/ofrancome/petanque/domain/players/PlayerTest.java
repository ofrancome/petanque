package com.ofrancome.petanque.domain.players;

import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.domain.seasons.Season;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.ofrancome.petanque.domain.TestUtils.createPlayer;

class PlayerTest {

    @Test
    @DisplayName("Should get latest Elo")
    public void shouldGetLatestElo() {
        Player player = createPlayer("Olivier", 1350);

        Season season = new Season();
        season.setId(2L);
        Ranking ranking = new Ranking();
        ranking.setId(2L);
        ranking.setElo(1300);
        season.addRanking(ranking);
        player.addRanking(ranking);

        Assertions.assertThat(player.currentElo()).isEqualTo(1300);
    }

    @Test
    @DisplayName("Should get accurate winrate - 50")
    public void shouldGetAccurateWinrate50() {
        Season season = new Season();
        season.setId(1L);

        Ranking rankingOlivier = new Ranking();
        rankingOlivier.setId(1L);
        rankingOlivier.setElo(1200);
        Player olivier = new Player();
        olivier.setId(1L);
        olivier.setName("Olivier");
        olivier.addRanking(rankingOlivier);
        season.addRanking(rankingOlivier);

        Ranking rankingTim = new Ranking();
        rankingTim.setId(2L);
        rankingTim.setElo(1200);
        Player tim = new Player();
        tim.setId(2L);
        tim.setName("Olivier");
        tim.addRanking(rankingTim);
        season.addRanking(rankingTim);


        Game loss = new Game();
        loss.setId(1L);
        loss.setEloSwitch(16);
        tim.addWin(loss);
        season.addGame(loss);
        olivier.addLoss(loss);
        Game win = new Game();
        win.setId(2L);
        win.setEloSwitch(16);
        olivier.addWin(win);
        tim.addLoss(win);
        season.addGame(win);

        Assertions.assertThat(olivier.getWinrate()).isEqualTo(50);
    }

    @Test
    @DisplayName("Should get accurate winrate - 66")
    public void shouldGetAccurateWinrate66() {
        Season season = new Season();
        season.setId(1L);

        Ranking rankingOlivier = new Ranking();
        rankingOlivier.setId(1L);
        rankingOlivier.setElo(1200);
        Player olivier = new Player();
        olivier.setId(1L);
        olivier.setName("Olivier");
        olivier.addRanking(rankingOlivier);
        season.addRanking(rankingOlivier);

        Ranking rankingTim = new Ranking();
        rankingTim.setId(2L);
        rankingTim.setElo(1200);
        Player tim = new Player();
        tim.setId(2L);
        tim.setName("Olivier");
        tim.addRanking(rankingTim);
        season.addRanking(rankingTim);


        Game loss = new Game();
        loss.setId(1L);
        loss.setEloSwitch(16);
        tim.addWin(loss);
        season.addGame(loss);
        olivier.addLoss(loss);
        Game win = new Game();
        win.setId(2L);
        win.setEloSwitch(16);
        olivier.addWin(win);
        tim.addLoss(win);
        season.addGame(win);
        Game otherWin = new Game();
        otherWin.setId(3L);
        otherWin.setEloSwitch(15);
        olivier.addWin(otherWin);
        tim.addLoss(otherWin);
        season.addGame(otherWin);

        Assertions.assertThat(olivier.getWinrate()).isEqualTo(66);
    }

    @Test
    @DisplayName("Should correctly add elo after win")
    public void shouldCorrectlyAddEloAfterWin() {
        Season season = new Season();
        season.setId(1L);

        Ranking ranking = new Ranking();
        ranking.setId(1L);
        ranking.setElo(1200);
        Player olivier = new Player();
        olivier.setId(1L);
        olivier.setName("Olivier");
        olivier.addRanking(ranking);
        season.addRanking(ranking);

        Game win = new Game();
        win.setId(1L);
        win.setEloSwitch(16);
        win.setLosersScore(2);
        win.setGameDay(LocalDate.of(2024, Month.APRIL, 25));
        season.addGame(win);
        olivier.addWin(win);

        Assertions.assertThat(olivier.currentElo()).isEqualTo(1216);

    }

    @Test
    @DisplayName("Should correctly deduce elo after loss")
    public void shouldCorrectlyDeduceEloAfterLoss() {
        Season season = new Season();
        season.setId(1L);

        Ranking ranking = new Ranking();
        ranking.setId(1L);
        ranking.setElo(1200);
        Player olivier = new Player();
        olivier.setId(1L);
        olivier.setName("Olivier");
        olivier.addRanking(ranking);
        season.addRanking(ranking);

        Game loss = new Game();
        loss.setId(1L);
        loss.setEloSwitch(16);
        loss.setLosersScore(2);
        loss.setGameDay(LocalDate.of(2024, Month.APRIL, 25));
        season.addGame(loss);
        olivier.addLoss(loss);

        Assertions.assertThat(olivier.currentElo()).isEqualTo(1184);

    }
}
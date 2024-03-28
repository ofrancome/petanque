package com.ofrancome.petanque.domain.elo;

import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.Ranking;
import com.ofrancome.petanque.domain.seasons.Season;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static com.ofrancome.petanque.domain.TestUtils.createPlayer;
import static org.assertj.core.api.Assertions.assertThat;

class EloCalculatorTest {

    private final EloCalculator eloCalculator = new EloCalculator();

    @Test
    @DisplayName("Elo switch should be correctlyCalculated - fair fight is 16")
    void eloSwitchShouldBeCorrectlyCalculatedFair() {
        Season season = new Season();
        season.setId(1L);
        season.setStart(LocalDate.of(2024, Month.APRIL, 25));
        assertThat(eloCalculator.getEloSwitch(Set.of(createPlayer("Olivier", 1200)),
                Set.of(createPlayer("Jon", 1200)), season)).isEqualTo(16);
    }

    @Test
    @DisplayName("Elo switch should be correctlyCalculated - less fair fight is 14")
    void eloSwitchShouldBeCorrectlyCalculatedLessFair() {
        Season season = new Season();
        season.setId(1L);
        season.setStart(LocalDate.of(2024, Month.APRIL, 25));
        assertThat(eloCalculator.getEloSwitch(
                Set.of(createPlayer("Olivier", 1225)),
                Set.of(createPlayer("Jon", 1175)),
                season)).isEqualTo(14);
    }

    @Test
    @DisplayName("Elo switch should be correctyl calculated - Multiple players per team fair is 16")
    void eloSwitchShouldBeCorrectylCalculatedMultiplePlayersPerTeamFairIs16() {
        Season season = new Season();
        season.setId(1L);
        season.setStart(LocalDate.of(2024, Month.APRIL, 25));
        assertThat(eloCalculator.getEloSwitch(
                Set.of(
                        createPlayer("Olivier", 1400),
                        createPlayer("Timothee", 1200),
                        createPlayer("Garrett", 1000)
                        ),
                Set.of(
                        createPlayer("Jon", 1200),
                        createPlayer("Simon", 1100),
                        createPlayer("Erwan", 1300)
                ),
                season
        )).isEqualTo(16);
    }
}
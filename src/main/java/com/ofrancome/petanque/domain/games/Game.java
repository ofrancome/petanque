package com.ofrancome.petanque.domain.games;

import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.seasons.Season;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate gameDay;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Season season;

    @ManyToMany
    @JoinTable(
        name = "winners",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> winners;

    @ManyToMany
    @JoinTable(
        name = "losers",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> losers;

    private Integer losersScore;
    private Integer eloSwitch;

    public void addWinner(Player player) {
        if (winners == null) {
            winners = new HashSet<>();
        }
        winners.add(player);
    }

    public void addLoser(Player player) {
        if (losers == null) {
            losers = new HashSet<>();
        }
        losers.add(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (!id.equals(game.id)) return false;
        if (!gameDay.equals(game.gameDay)) return false;
        if (!losersScore.equals(game.losersScore)) return false;
        return eloSwitch.equals(game.eloSwitch);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + gameDay.hashCode();
        result = 31 * result + losersScore.hashCode();
        result = 31 * result + eloSwitch.hashCode();
        return result;
    }
}

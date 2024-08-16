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

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate gameDay;

    @ManyToOne
    @JoinColumn(name = "season_id")
    //@JsonBackReference
    private Season season;

    @ManyToMany
    @JoinTable(
        name = "winners",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    //@JsonManagedReference
    private Set<Player> winners;

    @ManyToMany
    @JoinTable(
        name = "losers",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    //@JsonManagedReference
    private Set<Player> losers;

    private Integer losersScore;
    private Integer eloSwitch;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getGameDay() {
        return gameDay;
    }

    public void setGameDay(LocalDate gameDay) {
        this.gameDay = gameDay;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Set<Player> getWinners() {
        return winners;
    }

    public void setWinners(Set<Player> winners) {
        this.winners = winners;
    }

    public Set<Player> getLosers() {
        return losers;
    }

    public void setLosers(Set<Player> losers) {
        this.losers = losers;
    }

    public Integer getLosersScore() {
        return losersScore;
    }

    public void setLosersScore(Integer loserScore) {
        this.losersScore = loserScore;
    }

    public Integer getEloSwitch() {
        return eloSwitch;
    }

    public void setEloSwitch(Integer eloSwitch) {
        this.eloSwitch = eloSwitch;
    }

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

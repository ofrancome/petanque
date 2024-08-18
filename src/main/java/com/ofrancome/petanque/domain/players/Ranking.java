package com.ofrancome.petanque.domain.players;

import com.ofrancome.petanque.domain.seasons.Season;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "RANKING")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Season season;

    private Integer elo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Integer getElo() {
        return elo;
    }

    public void setElo(Integer elo) {
        this.elo = elo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ranking ranking = (Ranking) o;

        if (!id.equals(ranking.id)) return false;
        return Objects.equals(elo, ranking.elo);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (elo != null ? elo.hashCode() : 0);
        return result;
    }
}

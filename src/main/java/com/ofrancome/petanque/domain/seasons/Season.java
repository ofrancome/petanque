package com.ofrancome.petanque.domain.seasons;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.domain.players.Ranking;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "SEASON")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany
    @JsonManagedReference
    private Set<Game> games;

    @OneToMany
    @JsonManagedReference
    private Set<Ranking> rankings;

    @NotNull
    private LocalDate start;

    @Column(name = "endDate")
    private LocalDate end;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(Set<Ranking> rankings) {
        this.rankings = rankings;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void addRanking(Ranking ranking) {
        if (rankings == null) {
            rankings = new HashSet<>();
        }
        rankings.add(ranking);
        ranking.setSeason(this);
    }

    public void addGame(Game game) {
        if (games == null) {
            games = new HashSet<>();
        }
        games.add(game);
        game.setSeason(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Season season = (Season) o;

        if (!id.equals(season.id)) return false;
        if (!start.equals(season.start)) return false;
        return Objects.equals(end, season.end);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}

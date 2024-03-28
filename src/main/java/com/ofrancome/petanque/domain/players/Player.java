package com.ofrancome.petanque.domain.players;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.domain.seasons.Season;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.NaturalId;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PLAYER")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NaturalId
    private String name;
    private String avatar;

    @OneToMany
    @JsonManagedReference
    private Set<Ranking> rankings;

    @ManyToMany(mappedBy = "winners")
    @JsonBackReference
    private Set<Game> gamesWon;

    @ManyToMany(mappedBy = "losers")
    @JsonBackReference
    private Set<Game> gamesLost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Game> getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Set<Game> gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Set<Game> getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(Set<Game> gamesLost) {
        this.gamesLost = gamesLost;
    }

    public Set<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(Set<Ranking> rankings) {
        this.rankings = rankings;
    }

    public void addRanking(Ranking ranking) {
        if (rankings == null) {
            rankings = new HashSet<>();
        }
        rankings.add(ranking);
        ranking.setPlayer(this);
    }

    private void addElo(Season season, Integer eloSwitch) {
        rankings.forEach(ranking -> {
            if (ranking.getSeason().equals(season)) {
                ranking.setElo(ranking.getElo() + eloSwitch);
            }
        });
    }

    private void deduceElo(Season season, Integer eloSwitch) {
        addElo(season, eloSwitch * -1);
    }

    public void addWin(Game win) {
        if (gamesWon == null) {
            gamesWon = new HashSet<>();
        }
        gamesWon.add(win);
        win.addWinner(this);
        addElo(win.getSeason(), win.getEloSwitch());
    }

    public void addLoss(Game loss) {
        if (gamesLost == null) {
            gamesLost = new HashSet<>();
        }
        gamesLost.add(loss);
        loss.addLoser(this);
        deduceElo(loss.getSeason(), loss.getEloSwitch());

    }

    public Ranking lastRanking() {
        return rankings.stream().max(Comparator.comparing(ranking -> ranking.getSeason().getId())).orElseThrow();
    }

    public Integer getWinrate() {
        double nbWins = gamesWon.size();
        double nbLoss = gamesLost.size();
        double preciseWinrate = nbWins / (nbLoss + nbWins) * 100;
        return (int) preciseWinrate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        if (!name.equals(player.name)) return false;
        return Objects.equals(avatar, player.avatar);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }
}

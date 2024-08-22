package com.ofrancome.petanque.infra;

import com.ofrancome.petanque.domain.seasons.Season;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SeasonRepository extends CrudRepository<Season, Long> {
    @Query("SELECT s FROM Season s ORDER BY s.id DESC LIMIT 1")
    Optional<Season> currentSeason();
}

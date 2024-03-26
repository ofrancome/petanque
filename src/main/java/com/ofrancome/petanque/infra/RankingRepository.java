package com.ofrancome.petanque.infra;

import com.ofrancome.petanque.domain.players.Ranking;
import org.springframework.data.repository.CrudRepository;

public interface RankingRepository extends CrudRepository<Ranking, Long> {
}

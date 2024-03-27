package com.ofrancome.petanque.infra;

import com.ofrancome.petanque.domain.games.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

}

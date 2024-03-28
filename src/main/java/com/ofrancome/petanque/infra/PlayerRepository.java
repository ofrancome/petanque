package com.ofrancome.petanque.infra;

import com.ofrancome.petanque.domain.players.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    Optional<Player> findByName(String name);

    boolean existsByName(String name);
}

package com.ofrancome.petanque.infra;

import com.ofrancome.petanque.domain.players.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findByName(String name);
}

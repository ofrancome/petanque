package com.ofrancome.petanque.domain.players;

import com.ofrancome.petanque.domain.exceptions.PlayerAlreadyExistsException;
import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.infra.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    @DisplayName("Should throw player already exists")
    public void shouldThrowPlayerAlreadyExists() {
        Mockito.doReturn(true).when(playerRepository).existsByName("coucou");
        assertThrows(PlayerAlreadyExistsException.class, () -> playerService.addPlayer("coucou"));
    }

    @Test
    @DisplayName("Should return All Games for player")
    public void shouldReturnAllGamesForPlayer() {
        Player olivier = new Player();
        olivier.setName("Olivier");
        Game game1 = new Game();
        game1.setId(1L);
        Game game2 = new Game();
        game2.setId(2L);
        Game game3 = new Game();
        game3.setId(3L);
        Game game4 = new Game();
        game4.setId(4L);
        olivier.setGamesLost(Set.of(game1, game2));
        olivier.setGamesWon(Set.of(game3, game4));

        Mockito.doReturn(Optional.of(olivier)).when(playerRepository).findByName("Olivier");
        Optional<List<Game>> games = playerService.getAllGamesFor("Olivier");
        Assertions.assertTrue(games.isPresent());
        Assertions.assertEquals(4, games.get().size());
    }
}
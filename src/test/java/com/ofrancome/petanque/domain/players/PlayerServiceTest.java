package com.ofrancome.petanque.domain.players;

import com.ofrancome.petanque.domain.exceptions.PlayerAlreadyExistsException;
import com.ofrancome.petanque.infra.PlayerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

}
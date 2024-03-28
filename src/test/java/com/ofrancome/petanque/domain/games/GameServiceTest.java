package com.ofrancome.petanque.domain.games;

import com.ofrancome.petanque.app.dto.requests.GameCreationDto;
import com.ofrancome.petanque.domain.exceptions.PlayerDoesNotExistException;
import com.ofrancome.petanque.infra.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GameServiceImpl gameService;


    @Test
    @DisplayName("Should throw PlayerDoesNotExistException")
    public void shouldThrowPlayerDoesNotExistException() {
        Mockito.doReturn(Optional.empty()).when(playerRepository).findByName(Mockito.eq("coucou"));
        Assertions.assertThrows(PlayerDoesNotExistException.class, () -> gameService.addGame(Set.of("coucou"), Set.of("coucou"), 4));
    }

}
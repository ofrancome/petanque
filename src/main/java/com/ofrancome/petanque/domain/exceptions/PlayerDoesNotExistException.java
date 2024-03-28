package com.ofrancome.petanque.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PlayerDoesNotExistException extends RuntimeException {

    public PlayerDoesNotExistException() {
        super("Player does not exists");
    }

    public PlayerDoesNotExistException(String message) {
        super(message);
    }

    public PlayerDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerDoesNotExistException(Throwable cause) {
        super(cause);
    }
}

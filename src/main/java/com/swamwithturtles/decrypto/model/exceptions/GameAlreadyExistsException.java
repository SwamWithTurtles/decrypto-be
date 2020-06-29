package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class GameAlreadyExistsException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(GameAlreadyExistsException.class);

    public GameAlreadyExistsException(UUID gameId) {
        super(String.format("Game %s already exists", gameId.toString()));
        logger.warn(String.format("Game %s already exists", gameId.toString()));
    }
}

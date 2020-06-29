package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class GameInvalidStateException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(GameInvalidStateException.class);

    public GameInvalidStateException(UUID gameId) {
        super(String.format("Trying to perform an action on a game %s we were not expecting", gameId.toString()));
        logger.warn(String.format("Trying to perform an action on a game %s we were not expecting", gameId.toString()));
    }
}

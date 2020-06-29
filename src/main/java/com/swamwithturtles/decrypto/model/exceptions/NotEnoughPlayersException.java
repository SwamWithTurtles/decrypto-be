package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class NotEnoughPlayersException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(NotEnoughPlayersException.class);

    public NotEnoughPlayersException(UUID gameId) {
        super(String.format("Cannot start game %s with fewer than four players", gameId.toString()));
        logger.warn(String.format("Cannot start game %s with fewer than four players", gameId.toString()));
    }
}
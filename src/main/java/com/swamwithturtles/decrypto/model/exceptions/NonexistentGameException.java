package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NonexistentGameException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(NonexistentGameException.class);

    public NonexistentGameException(UUID gameId) {
        super(String.format("Game %s does not exists (but should)", gameId.toString()));
        logger.warn(String.format("Game %s does not exists (but should)", gameId.toString()));
    }
}

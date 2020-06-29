package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class DuplicatePlayerException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(DuplicatePlayerException.class);

    public DuplicatePlayerException(UUID gameId, String duplicateName) {
        super(String.format("Name %s already exists in game ID %s", duplicateName, gameId.toString()));
        logger.warn(String.format("Name %s already exists in game ID %s", duplicateName, gameId.toString()));
    }
}

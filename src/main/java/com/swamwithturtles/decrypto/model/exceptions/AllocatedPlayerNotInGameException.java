package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllocatedPlayerNotInGameException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(AllocatedPlayerNotInGameException.class);

    public AllocatedPlayerNotInGameException(UUID gameId, String name) {
        super(String.format("Player %s is not game %s", gameId.toString(), name));
        logger.warn(String.format("Player %s is not game %s", gameId.toString(), name));
    }

}

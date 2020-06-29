package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordlistsAlreadyGeneratedException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(WordlistsAlreadyGeneratedException.class);

    public WordlistsAlreadyGeneratedException(UUID gameId) {
        super(String.format("Trying to duplicate wordlists for game %s when they already exist", gameId.toString()));
        logger.warn(String.format("Trying to duplicate wordlists for game %s when they already exist", gameId.toString()));
    }
}

package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EndingTurnBeforeAllGuessesIn  extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(GameInvalidStateException.class);

    public EndingTurnBeforeAllGuessesIn(UUID gameId) {
        super(String.format("Ending turn of game %s before all guesses in", gameId.toString()));
        logger.warn(String.format("Ending turn of game %s before all guesses in", gameId.toString()));
    }
}

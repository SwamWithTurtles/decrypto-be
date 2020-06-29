package com.swamwithturtles.decrypto.model.exceptions;

import java.util.List;
import java.util.UUID;

public class InvalidGuessWordsSizeException extends InvalidStateException {
    public InvalidGuessWordsSizeException(UUID gameId, List<String> guessWords) {
        super("Invalid number of words");
    }
}

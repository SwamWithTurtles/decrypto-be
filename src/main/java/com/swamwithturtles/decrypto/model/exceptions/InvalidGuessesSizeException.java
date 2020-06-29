package com.swamwithturtles.decrypto.model.exceptions;

import java.util.List;
import java.util.UUID;

public class InvalidGuessesSizeException extends InvalidStateException {
    public InvalidGuessesSizeException(UUID gameId, List<Integer> guesses) {
        super("Woo");
    }
}

package com.swamwithturtles.decrypto.model.exceptions;

import com.swamwithturtles.decrypto.model.Team;
import java.util.UUID;

public class AlreadyProvidedGuesswordsException extends InvalidStateException {
    public AlreadyProvidedGuesswordsException(UUID gameId, Team team) {
        super("Already got it love");
    }
}

package com.swamwithturtles.decrypto.model.exceptions;

import com.swamwithturtles.decrypto.model.Parity;
import com.swamwithturtles.decrypto.model.Team;
import java.util.UUID;

public class AlreadyProvidedGuessesException extends InvalidStateException {
    public AlreadyProvidedGuessesException(UUID gameId, Team team, Parity parity) {
        super("OH NO");
    }
}

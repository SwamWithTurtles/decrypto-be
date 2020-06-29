package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;

public class InvalidClueGiverException extends InvalidStateException {
    public InvalidClueGiverException(UUID gameId, String playerName) {
        super("NOT YOUR TURN " + playerName);
    }
}

package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.model.exceptions.DuplicatePlayerException;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.UUID;

public class RegisterPlayer extends GameStateEvent {

    private final String playerName;

    public RegisterPlayer(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isValid(GameAggregate gameAggregate, UUID gameId) {
        if(gameAggregate.getGameState() != GameStatus.WAITING) {
            throw new GameInvalidStateException(gameId);
        }

        boolean playerAlreadyExists = gameAggregate.getPlayers().stream()
                .anyMatch(p -> p.getName().equals(playerName));

        if(playerAlreadyExists) {
            throw new DuplicatePlayerException(gameId, playerName);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "RegisterPlayer";
    }
}

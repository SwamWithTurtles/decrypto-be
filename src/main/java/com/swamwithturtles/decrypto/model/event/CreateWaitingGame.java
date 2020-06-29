package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.model.exceptions.GameAlreadyExistsException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import java.util.UUID;

public class CreateWaitingGame extends GameStateEvent {

    private String message;

    public CreateWaitingGame() {
        this.message = "MESSAGE";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValid(GameAggregate game, UUID gameId) {
        if(game != null) {
            throw new GameAlreadyExistsException(gameId);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "CreateWaitingGame";
    }
}

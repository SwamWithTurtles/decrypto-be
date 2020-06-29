package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class ClearDownModel extends GameStateEvent {

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        if(game.getGameState() != GameStatus.END_OF_TURN_TRANSITION) {
            throw new GameInvalidStateException(gameId);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "ClearDownModel";
    }
}

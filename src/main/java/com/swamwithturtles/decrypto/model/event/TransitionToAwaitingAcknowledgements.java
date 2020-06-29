package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class TransitionToAwaitingAcknowledgements extends GameStateEvent {
    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        return game.getGameState() == GameStatus.END_OF_TURN_TRANSITION;
    }

    @Override
    public String getEventName() {
        return "TransitionToAwaitingAcknowledgements";
    }
}

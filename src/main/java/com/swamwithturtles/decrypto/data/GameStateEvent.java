package com.swamwithturtles.decrypto.data;

import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public abstract class GameStateEvent {

    public abstract boolean isValid(GameAggregate game, UUID gameId);

    public void postHook(GameStateMachine gameStateMachine, GameAggregate game, UUID gameId) {
        //Do Nothing
    }

    public abstract String getEventName();
}

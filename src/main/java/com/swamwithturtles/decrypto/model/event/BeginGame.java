package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.sagas.BeginTurnSaga;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.UUID;

public class BeginGame extends GameStateEvent {

    public boolean isValid(GameAggregate gameAggregate, UUID gameId) {
        if(gameAggregate.getGameState() != GameStatus.GENERATING) {
            throw new GameInvalidStateException(gameId);
        }

        return true;
    }

    public void postHook(GameStateMachine gameStateMachine, GameAggregate game, UUID gameId) {
        BeginTurnSaga beginTurnSaga = new BeginTurnSaga(gameStateMachine);
        beginTurnSaga.execute(gameId);
    }

    @Override
    public String getEventName() {
        return "BeginGame";
    }
}

package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.sagas.BeginTurnSaga;
import com.swamwithturtles.decrypto.model.sagas.EndOfGameSaga;
import com.swamwithturtles.decrypto.model.sagas.EndOfTurnSaga;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class CheckForVictory extends GameStateEvent {

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        if(game.getGameState() != GameStatus.END_OF_TURN_TRANSITION) {
            throw new GameInvalidStateException(gameId);
        }

        return true;
    }

    @Override
    public void postHook(GameStateMachine gameStateMachine, GameAggregate game, UUID gameId) {
        if(game.getScore().hasVictoryCondition()) {
            EndOfGameSaga endOfGameSaga = new EndOfGameSaga(gameStateMachine);
            endOfGameSaga.execute(gameId);
        } else {
            new EndOfTurnSaga(gameStateMachine).execute(gameId);
        }
    }

    @Override
    public String getEventName() {
        return "CheckForVictory";
    }
}

package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.exceptions.NotEnoughPlayersException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.sagas.BeginGameSaga;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.UUID;

public class MoveGameOutOfWaiting extends GameStateEvent {

    public boolean isValid(GameAggregate gameAggregate, UUID gameId) {
        if(gameAggregate.getGameState() != GameStatus.WAITING) {
            throw new GameInvalidStateException(gameId);
        }

        if(gameAggregate.getPlayers().size() < 4) {
            throw new NotEnoughPlayersException(gameId);
        }

        return true;
    }

    public void postHook(GameStateMachine gsm, GameAggregate game, UUID gameId) {
        BeginGameSaga beginGameSaga = new BeginGameSaga(gsm);
        beginGameSaga.generateNecessaryState(gameId);
    }

    @Override
    public String getEventName() {
        return "MoveGameOutOfWaiting";
    }
}

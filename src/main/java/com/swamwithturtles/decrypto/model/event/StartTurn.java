package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.sagas.ReadyForGuessingSaga;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.UUID;

public class StartTurn extends GameStateEvent {

    public boolean isValid(GameAggregate gameAggregate, UUID gameId) {
        if(gameAggregate.getGameState() != GameStatus.BETWEEN_TURNS) {
            throw new GameInvalidStateException(gameId);
        }

        if(gameAggregate.getBlueCodephrase() == null || gameAggregate.getRedCodephrase() == null) {
            throw new GameInvalidStateException(gameId);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "StartTurn";
    }
}

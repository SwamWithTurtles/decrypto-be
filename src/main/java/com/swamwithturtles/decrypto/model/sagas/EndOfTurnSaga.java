package com.swamwithturtles.decrypto.model.sagas;

import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.event.TransitionToAwaitingAcknowledgements;
import java.util.UUID;

public class EndOfTurnSaga {

    private GameStateMachine gameStateMachine;

    public EndOfTurnSaga(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    public void execute(UUID gameId) {
        gameStateMachine.apply(gameId, new TransitionToAwaitingAcknowledgements());
    }
}

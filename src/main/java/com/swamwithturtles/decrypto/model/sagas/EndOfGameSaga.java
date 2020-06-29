package com.swamwithturtles.decrypto.model.sagas;

import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.event.EndGame;
import java.util.UUID;

public class EndOfGameSaga {

    private GameStateMachine gameStateMachine;

    public EndOfGameSaga(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    public void execute(UUID gameId) {
        EndGame endGame = new EndGame();

        gameStateMachine.apply(gameId, endGame);
    }
}

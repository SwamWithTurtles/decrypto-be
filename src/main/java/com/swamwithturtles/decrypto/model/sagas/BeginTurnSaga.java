package com.swamwithturtles.decrypto.model.sagas;

import com.swamwithturtles.decrypto.model.event.AssignNewCodePhrases;
import com.swamwithturtles.decrypto.model.event.BeginTurn;
import com.swamwithturtles.decrypto.model.event.StartTurn;
import com.swamwithturtles.decrypto.model.CodePhrase;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import java.util.UUID;

public class BeginTurnSaga {

    private GameStateMachine gameStateMachine;

    public BeginTurnSaga(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    public void execute(UUID gameId) {
        gameStateMachine.apply(gameId, new BeginTurn());
        assignNewCodePhrases(gameId);
        startTurn(gameId);
    }

    private void assignNewCodePhrases(UUID gameId) {
        CodePhrase redCodephrase = CodePhrase.random();
        CodePhrase blueCodephrase = CodePhrase.random();

        gameStateMachine.apply(gameId, new AssignNewCodePhrases(redCodephrase, Team.RED_TEAM));
        gameStateMachine.apply(gameId, new AssignNewCodePhrases(blueCodephrase, Team.BLUE_TEAM));
    }

    private void startTurn(UUID gameId) {
        gameStateMachine.apply(gameId, new StartTurn());
    }
}

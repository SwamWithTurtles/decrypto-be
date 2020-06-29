package com.swamwithturtles.decrypto.model.sagas;

import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.Parity;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.model.event.MigrateToNewGuess;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class ReadyForGuessingSaga {
    private final GameStateMachine gameStateMachine;

    public ReadyForGuessingSaga(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    public void execute(UUID gameId) {
        GameAggregate game = gameStateMachine.getGameById(gameId);

        if(game.getTurnNumber() == 0) {
            gameStateMachine.apply(gameId, new MigrateToNewGuess(Team.RED_TEAM, Parity.FRIENDLY));
        } else {
            gameStateMachine.apply(gameId, new MigrateToNewGuess(Team.RED_TEAM, Parity.OPPOSITION));
        }

    }
}

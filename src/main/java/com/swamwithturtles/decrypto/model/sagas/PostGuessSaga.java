package com.swamwithturtles.decrypto.model.sagas;

import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.GameStatus;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_BLUE_FRIENDLY;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_BLUE_OPPOSITION;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_RED_FRIENDLY;
import static com.swamwithturtles.decrypto.model.Parity.FRIENDLY;
import static com.swamwithturtles.decrypto.model.Parity.OPPOSITION;
import static com.swamwithturtles.decrypto.model.Team.BLUE_TEAM;
import static com.swamwithturtles.decrypto.model.Team.RED_TEAM;
import com.swamwithturtles.decrypto.model.event.MigrateToNewGuess;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class PostGuessSaga {

    private final GameStateMachine gameStateMachine;

    public PostGuessSaga(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    public void execute(UUID gameId) {
        GameAggregate game = gameStateMachine.getGameById(gameId);

        if(game.getGameState() == GameStatus.AWAITING_GUESSES_RED_OPPOSITION) {
            gameStateMachine.apply(gameId, new MigrateToNewGuess(BLUE_TEAM, OPPOSITION));
        } else if(game.getGameState() == AWAITING_GUESSES_BLUE_OPPOSITION) {
            gameStateMachine.apply(gameId, new MigrateToNewGuess(RED_TEAM, FRIENDLY));
        } else if(game.getGameState() == AWAITING_GUESSES_RED_FRIENDLY) {
            gameStateMachine.apply(gameId, new MigrateToNewGuess(BLUE_TEAM, FRIENDLY));
        } else if(game.getGameState() == AWAITING_GUESSES_BLUE_FRIENDLY) {
            new MarkingSaga(gameStateMachine).execute(gameId);
        }
    }
}

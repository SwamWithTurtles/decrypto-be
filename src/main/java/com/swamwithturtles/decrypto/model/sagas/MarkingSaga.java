package com.swamwithturtles.decrypto.model.sagas;

import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.ClueWord;
import com.swamwithturtles.decrypto.model.CodePhrase;
import com.swamwithturtles.decrypto.model.Score;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.model.event.ArchivePublicGuesses;
import com.swamwithturtles.decrypto.model.event.CheckForVictory;
import com.swamwithturtles.decrypto.model.event.ClearDownModel;
import com.swamwithturtles.decrypto.model.event.MarkGuesses;
import com.swamwithturtles.decrypto.model.event.TransitionToEndOfTurn;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.List;
import java.util.UUID;

public class MarkingSaga {

    private GameStateMachine gameStateMachine;

    public MarkingSaga(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    public void execute(UUID gameId) {

        TransitionToEndOfTurn transitionToEndOfTurn = new TransitionToEndOfTurn();

        gameStateMachine.apply(gameId, transitionToEndOfTurn);

        GameAggregate game = gameStateMachine.getGameById(gameId);
        MarkGuesses markGuesses = new MarkGuesses(Score.fromGameState(game));

        gameStateMachine.apply(gameId, markGuesses);

        List<String> blueGuesswords = game.getBlueGuessWords();
        List<String> redGuesswords = game.getRedGuessWords();
        CodePhrase blueCodephrase = game.getBlueCodephrase();
        CodePhrase redCodephrase = game.getRedCodephrase();

        for (int i = 0; i < 3; i++) {
            ClueWord blueClue = new ClueWord(blueGuesswords.get(i), Team.BLUE_TEAM, blueCodephrase.getUnderlying().get(i));
            ClueWord redClue = new ClueWord(redGuesswords.get(i), Team.RED_TEAM, redCodephrase.getUnderlying().get(i));

            ArchivePublicGuesses blueIxGuess = new ArchivePublicGuesses(blueClue);
            ArchivePublicGuesses redIxGuess = new ArchivePublicGuesses(redClue);

            gameStateMachine.apply(gameId, blueIxGuess);
            gameStateMachine.apply(gameId, redIxGuess);
        }

        ClearDownModel clearDownModel = new ClearDownModel();

        gameStateMachine.apply(gameId, clearDownModel);

        CheckForVictory checkForVictory = new CheckForVictory();

        gameStateMachine.apply(gameId, checkForVictory);


    }
}

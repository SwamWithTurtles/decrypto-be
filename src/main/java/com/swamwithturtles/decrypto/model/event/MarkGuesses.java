package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.model.Score;
import com.swamwithturtles.decrypto.model.exceptions.EndingTurnBeforeAllGuessesIn;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class MarkGuesses extends GameStateEvent {

    private final Score score;

    public MarkGuesses(Score score) {
        this.score = score;
    }

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        if(game.getGameState() != GameStatus.END_OF_TURN_TRANSITION)  {
            throw new GameInvalidStateException(gameId);
        }

        if(!game.getTurnNumber().equals(0)) {
            if(game.getOppositionGuessesFromBlue() == null || game.getOppositionGuessesFromRed() == null) {
                throw new EndingTurnBeforeAllGuessesIn(gameId);
            }
        }

        if(game.getFriendlyGuessesFromBlue() == null || game.getFriendlyGuessesFromRed() == null ||
                game.getBlueCodephrase() == null || game.getRedCodephrase() == null) {
            throw new EndingTurnBeforeAllGuessesIn(gameId);
        }

        return true;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public String getEventName() {
        return "MarkGuesses";
    }
}

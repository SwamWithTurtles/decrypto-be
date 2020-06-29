package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.model.exceptions.EndingTurnBeforeAllGuessesIn;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class TransitionToEndOfTurn extends GameStateEvent {

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        if(game.getGameState() != GameStatus.AWAITING_GUESSES_BLUE_FRIENDLY) {
            throw new GameInvalidStateException(gameId);
        }

        if(!game.getTurnNumber().equals(0)) {
            if(game.getOppositionGuessesFromBlue() == null || game.getOppositionGuessesFromRed() == null) {
                throw new EndingTurnBeforeAllGuessesIn(gameId);
            }
        }

        if(game.getFriendlyGuessesFromBlue() == null || game.getFriendlyGuessesFromRed() == null) {
            throw new EndingTurnBeforeAllGuessesIn(gameId);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "TransitionToEndOfTurn";
    }
}

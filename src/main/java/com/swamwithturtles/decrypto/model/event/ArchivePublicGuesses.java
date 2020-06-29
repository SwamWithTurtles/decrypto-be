package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.ClueWord;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class ArchivePublicGuesses extends GameStateEvent {

    private final ClueWord clueWord;

    public ArchivePublicGuesses(ClueWord clueWord) {
        this.clueWord = clueWord;
    }

    public ClueWord getClueWord() {
        return clueWord;
    }

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        if(game.getGameState() != GameStatus.END_OF_TURN_TRANSITION) {
            throw new GameInvalidStateException(gameId);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "ArchivePublicGuesses";
    }
}

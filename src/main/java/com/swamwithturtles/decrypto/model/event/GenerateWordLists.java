package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.exceptions.WordlistsAlreadyGeneratedException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.WordList;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.UUID;

public class GenerateWordLists extends GameStateEvent {

    private final WordList teamOneWordlist;
    private final WordList teamTwoWordlist;

    public GenerateWordLists(WordList teamOneWordlist, WordList teamTwoWordlist) {
        this.teamOneWordlist = teamOneWordlist;
        this.teamTwoWordlist = teamTwoWordlist;
    }

    public WordList getTeamOneWordlist() {
        return teamOneWordlist;
    }

    public WordList getTeamTwoWordlist() {
        return teamTwoWordlist;
    }

    public boolean isValid(GameAggregate game, UUID gameId) {
        if(game.getGameState() != GameStatus.GENERATING) {
            throw new GameInvalidStateException(gameId);
        }

        if(game.getRedWordlist() != null && game.getBlueWordlist() != null) {
            throw new WordlistsAlreadyGeneratedException(gameId);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "GenerateWordLists";
    }
}

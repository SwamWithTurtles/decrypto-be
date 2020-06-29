package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.model.exceptions.AlreadyProvidedGuesswordsException;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.exceptions.InvalidClueGiverException;
import com.swamwithturtles.decrypto.model.exceptions.InvalidGuessWordsSizeException;
import com.swamwithturtles.decrypto.model.sagas.ReadyForGuessingSaga;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.List;
import java.util.UUID;

public class ProvideGuessWord extends GameStateEvent {

    private String playerName;
    private List<String> guessWords;
    private Team team;

    public ProvideGuessWord(String playerName, List<String> guessWords, Team team) {
        this.playerName = playerName;
        this.guessWords = guessWords;
        this.team = team;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getGuessWords() {
        return guessWords;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        if(guessWords.size() != 3) {
            throw new InvalidGuessWordsSizeException(gameId, guessWords);
        }

        if (game.getGameState() != GameStatus.AWAITING_GUESSWORDS) {
            throw new GameInvalidStateException(gameId);
        }

        if(!game.getClueGiver(team).getName().equals(playerName)) {
            throw new InvalidClueGiverException(gameId, playerName);
        }

        if(team == Team.RED_TEAM && game.getRedGuessWords() != null) {
            throw new AlreadyProvidedGuesswordsException(gameId, team);
        }

        if(team == Team.BLUE_TEAM && game.getBlueGuessWords() != null) {
            throw new AlreadyProvidedGuesswordsException(gameId, team);
        }

        return true;
    }

    @Override
    public void postHook(GameStateMachine gameStateMachine, GameAggregate game, UUID gameId) {
        if(game.getRedGuessWords() != null && game.getBlueGuessWords() != null) {
            new ReadyForGuessingSaga(gameStateMachine).execute(gameId);
        }
    }

    @Override
    public String getEventName() {
        return "ProvideGuessWord";
    }
}

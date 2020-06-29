package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_BLUE_FRIENDLY;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_BLUE_OPPOSITION;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_RED_FRIENDLY;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_RED_OPPOSITION;
import com.swamwithturtles.decrypto.model.Parity;
import com.swamwithturtles.decrypto.model.Player;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.model.exceptions.AlreadyProvidedGuessesException;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.exceptions.InvalidClueGiverException;
import com.swamwithturtles.decrypto.model.exceptions.InvalidGuessesSizeException;
import com.swamwithturtles.decrypto.model.sagas.MarkingSaga;
import com.swamwithturtles.decrypto.model.sagas.PostGuessSaga;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProvideGuess extends GameStateEvent {

    private final String playerName;

    private final List<Integer> guesses;

    private final Team team;

    private final Parity parity;

    public ProvideGuess(String playerName, List<Integer> guessWords, Team team, Parity parity) {
        this.playerName = playerName;
        this.guesses = guessWords;
        this.team = team;
        this.parity = parity;
    }

    public Team getTeam() {
        return team;
    }

    public List<Integer> getGuesses() {
        return guesses;
    }

    public Parity getParity() {
        return parity;
    }

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        if (guesses.size() != 3) {
            throw new InvalidGuessesSizeException(gameId, guesses);
        }

        if(!Arrays.asList(AWAITING_GUESSES_BLUE_FRIENDLY, AWAITING_GUESSES_BLUE_OPPOSITION, AWAITING_GUESSES_RED_OPPOSITION, AWAITING_GUESSES_RED_FRIENDLY).contains(game.getGameState())) {
            throw new GameInvalidStateException(gameId);
        }

        if(game.getGameState() == AWAITING_GUESSES_RED_OPPOSITION && (team != Team.RED_TEAM || parity != Parity.OPPOSITION)) {
            throw new GameInvalidStateException(gameId);
        }

        if(game.getGameState() == AWAITING_GUESSES_BLUE_OPPOSITION && (team != Team.BLUE_TEAM || parity != Parity.OPPOSITION)) {
            throw new GameInvalidStateException(gameId);
        }

        if(game.getGameState() == AWAITING_GUESSES_RED_FRIENDLY && (team != Team.RED_TEAM || parity != Parity.FRIENDLY)) {
            throw new GameInvalidStateException(gameId);
        }

        if(game.getGameState() == AWAITING_GUESSES_BLUE_FRIENDLY && (team != Team.BLUE_TEAM || parity != Parity.FRIENDLY)) {
            throw new GameInvalidStateException(gameId);
        }

        Optional<Player> playerInGame = game.getPlayers().stream().filter(p -> p.getName().equals(playerName)).findFirst();

        if (!playerInGame.isPresent()) {
            throw new InvalidClueGiverException(gameId, playerName);
        }

        if (playerInGame.get().getTeam() != team) {
            throw new InvalidClueGiverException(gameId, playerName);
        }

        if (parity == Parity.FRIENDLY) {
            if (game.getClueGiver(team).getName().equals(playerName)) {
                throw new InvalidClueGiverException(gameId, playerName);
            }

            if (team == Team.BLUE_TEAM && game.getFriendlyGuessesFromBlue() != null) {
                throw new AlreadyProvidedGuessesException(gameId, team, parity);
            }

            if (team == Team.RED_TEAM && game.getFriendlyGuessesFromRed() != null) {
                throw new AlreadyProvidedGuessesException(gameId, team, parity);
            }
        }

        if (parity == Parity.OPPOSITION) {
            if (team == Team.BLUE_TEAM && game.getOppositionGuessesFromBlue() != null) {
                throw new AlreadyProvidedGuessesException(gameId, team, parity);
            }

            if (team == Team.RED_TEAM && game.getOppositionGuessesFromRed() != null) {
                throw new AlreadyProvidedGuessesException(gameId, team, parity);
            }
        }

        if (game.getTurnNumber() == 0 && parity == Parity.OPPOSITION) {
            throw new GameInvalidStateException(gameId);
        }

        return true;
    }

    @Override
    public void postHook(GameStateMachine gameStateMachine, GameAggregate game, UUID gameId) {

        new PostGuessSaga(gameStateMachine).execute(gameId);

        if (game.getTurnNumber() == 0) {

            // No opponent guessing on turn 0

            if(game.getFriendlyGuessesFromRed() != null && game.getFriendlyGuessesFromBlue() != null) {
                new MarkingSaga(gameStateMachine).execute(gameId);
            }
        } else {

            if (game.getFriendlyGuessesFromRed() != null &&
                    game.getFriendlyGuessesFromBlue() != null &&
                    game.getOppositionGuessesFromRed() != null &&
                    game.getOppositionGuessesFromBlue() != null) {
                new MarkingSaga(gameStateMachine).execute(gameId);
            }
        }
    }

    @Override
    public String getEventName() {
        return "ProvideGuess";
    }
}

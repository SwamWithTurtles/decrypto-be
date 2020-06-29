package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_BLUE_OPPOSITION;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_RED_FRIENDLY;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSES_RED_OPPOSITION;
import static com.swamwithturtles.decrypto.model.GameStatus.AWAITING_GUESSWORDS;
import com.swamwithturtles.decrypto.model.Parity;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.Arrays;
import java.util.UUID;

public class MigrateToNewGuess extends GameStateEvent {

    private Team team;
    private Parity parity;

    public MigrateToNewGuess(Team team, Parity parity) {
        this.team = team;
        this.parity = parity;
    }

    public Team getTeam() {
        return team;
    }

    public Parity getParity() {
        return parity;
    }

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        return Arrays.asList(AWAITING_GUESSES_RED_OPPOSITION, AWAITING_GUESSES_BLUE_OPPOSITION, AWAITING_GUESSES_RED_FRIENDLY, AWAITING_GUESSWORDS).contains(game.getGameState());
    }

    @Override
    public String getEventName() {
        return "MigrateToNewGuess";
    }
}

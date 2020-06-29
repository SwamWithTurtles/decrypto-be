package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.model.sagas.BeginTurnSaga;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.UUID;

public class AcknowledgeResults extends GameStateEvent {

    private final String playerName;

    public AcknowledgeResults(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public boolean isValid(GameAggregate game, UUID gameId) {
        return game.getGameState() == GameStatus.AWAITING_ACKNOWLEDGEMENTS &&
                game.getAwaitingPlayers().stream().anyMatch(p -> p.getName().equals(playerName));
    }

    public void postHook(GameStateMachine gameStateMachine, GameAggregate game, UUID gameId) {
        if(game.getAwaitingPlayers().isEmpty()) {
            new BeginTurnSaga(gameStateMachine).execute(gameId);
        }
    }

    @Override
    public String getEventName() {
        return "AcknowledgeResults";
    }
}

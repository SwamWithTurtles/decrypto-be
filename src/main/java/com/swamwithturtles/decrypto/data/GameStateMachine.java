package com.swamwithturtles.decrypto.data;

import com.swamwithturtles.decrypto.model.event.CreateWaitingGame;
import com.swamwithturtles.decrypto.model.exceptions.InvalidStateException;
import com.swamwithturtles.decrypto.model.exceptions.NonexistentGameException;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameStateMachine {


    private static final Logger logger = LogManager.getLogger(GameStateMachine.class);

    private final GameEventStream eventStream;


    @Autowired
    public GameStateMachine(GameEventStream eventStream) {
        this.eventStream = eventStream;
    }

    public void apply(UUID gameId, GameStateEvent gameStateEvent) {
        GameAggregate aggregate = eventStream.getGame(gameId);

        logger.info(gameStateEvent);


        if(aggregate == null && !(gameStateEvent instanceof CreateWaitingGame)) {
            throw new NonexistentGameException(gameId);
        }

        if(!gameStateEvent.isValid(aggregate, gameId)) {
            throw new InvalidStateException("Problem");
        }

        eventStream.persistEvent(gameId, gameStateEvent);

        GameAggregate updatedGame = getGameById(gameId);

        gameStateEvent.postHook(this, updatedGame, gameId);
    }

    public GameAggregate getGameById(UUID id) {
        return eventStream.getGame(id);
    }

    public List<GameStateEvent> getExistingEventsForGame(UUID id) {
        return eventStream.getFullEventStreamForGame(id);
    }
}

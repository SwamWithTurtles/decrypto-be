package com.swamwithturtles.decrypto.data;

import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameEventStream {

    private static Map<UUID, List<GameStateEvent>> gameStateEventList = new HashMap<>();

    @Autowired
    private SimpMessagingTemplate template;

    public void persistEvent(UUID gameId, GameStateEvent event) {
        if(!gameStateEventList.containsKey(gameId)) {
            gameStateEventList.put(gameId, new ArrayList<>());
        }

        gameStateEventList.get(gameId).add(event);
        this.template.convertAndSend("/game/messages/" + gameId, event);
    }

    public GameAggregate getGame(UUID gameId) {
        if(!gameStateEventList.containsKey(gameId)) {
            return null;
        }

        return new GameAggregate().fromEventStream(gameStateEventList.get(gameId));
    }

    public List<GameStateEvent> getFullEventStreamForGame(UUID id) {
        return gameStateEventList.get(id);
    }
}

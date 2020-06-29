package com.swamwithturtles.decrypto.controller;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.event.CreateWaitingGame;
import com.swamwithturtles.decrypto.model.event.RegisterPlayer;
import com.swamwithturtles.decrypto.model.event.MoveGameOutOfWaiting;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GameWaitingRoomController {

    private final GameStateMachine gameStateMachine;

    @Autowired
    public GameWaitingRoomController(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    @PostMapping(path="/game")
    public UUID createGame() {
        UUID gameId = UUID.randomUUID();
        CreateWaitingGame createWaitingGameEvent = new CreateWaitingGame();

        gameStateMachine.apply(gameId, createWaitingGameEvent);

        return gameId;
    }

    @GetMapping(path="/game/{gameId}")
    public GameAggregate getGameDetails(@PathVariable("gameId") UUID gameId) {
        return gameStateMachine.getGameById(gameId);
    }

    @PostMapping(path="/game/{gameId}/player/{playerName}")
    public UUID registerPlayer(@PathVariable("gameId") UUID gameId, @PathVariable("playerName") String playerName) {
        RegisterPlayer registerPlayerEvent = new RegisterPlayer(playerName);

        gameStateMachine.apply(gameId, registerPlayerEvent);

        return gameId;
    }

    @PostMapping(path="/game/{gameId}/start")
    public UUID start(@PathVariable("gameId") UUID gameId) {
        MoveGameOutOfWaiting startGameEvent = new MoveGameOutOfWaiting();

        gameStateMachine.apply(gameId, startGameEvent);

        return gameId;
    }

    @GetMapping(path="/game/{gameId}/events")
    public List<GameStateEvent> getGameEvents(@PathVariable("gameId") UUID gameId) {
        return gameStateMachine.getExistingEventsForGame(gameId);
    }
}

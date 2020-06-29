package com.swamwithturtles.decrypto.controller;

import com.swamwithturtles.decrypto.controller.requests.GuessWordSubmission;
import com.swamwithturtles.decrypto.controller.requests.GuessesSubmission;
import com.swamwithturtles.decrypto.controller.requests.ResultAcknowledgement;
import com.swamwithturtles.decrypto.model.event.AcknowledgeResults;
import com.swamwithturtles.decrypto.model.event.ProvideGuessWord;
import com.swamwithturtles.decrypto.model.event.ProvideGuess;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class GameInputController {

    private final GameStateMachine gameStateMachine;

    @Autowired
    public GameInputController(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    @PostMapping(path="/game/{gameId}/guesswords")
    public UUID createGame(@PathVariable UUID gameId, @RequestBody GuessWordSubmission guestWordSubmission) {
        ProvideGuessWord provideGuess = new ProvideGuessWord(
                guestWordSubmission.getPlayerName(),
                guestWordSubmission.getGuessWords(),
                guestWordSubmission.getTeam()
        );

        gameStateMachine.apply(gameId, provideGuess);

        return gameId;
    }

    @PostMapping(path="/game/{gameId}/guesses")
    public UUID createGame(@PathVariable UUID gameId, @RequestBody GuessesSubmission guessesSubmission) {
        ProvideGuess provideGuess = new ProvideGuess(
                guessesSubmission.getPlayerName(),
                guessesSubmission.getGuess(),
                guessesSubmission.getTeam(),
                guessesSubmission.getParity()
        );

        gameStateMachine.apply(gameId, provideGuess);

        return gameId;
    }

    @PostMapping(path="/game/{gameId}/turn-end")
    public UUID createGame(@PathVariable UUID gameId, @RequestBody ResultAcknowledgement acknowledgeResults) {
        AcknowledgeResults provideGuess = new AcknowledgeResults(
                acknowledgeResults.getPlayerName()
        );

        gameStateMachine.apply(gameId, provideGuess);

        return gameId;
    }
}

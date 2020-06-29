package com.swamwithturtles.decrypto.stateMachine;

import com.swamwithturtles.decrypto.model.CodePhrase;
import com.swamwithturtles.decrypto.model.Parity;
import com.swamwithturtles.decrypto.model.Player;
import com.swamwithturtles.decrypto.model.Score;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.model.WordList;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.List;

public class GameAggregateBuilder {

    private GameAggregate game;

    private List<Player> players;
    private GameStatus gameState;

    private WordList redWordlist;
    private WordList blueWordlist;

    private List<Player> redTurnOrder;
    private List<Player> blueTurnOrder;

    private CodePhrase redCodephrase;
    private CodePhrase blueCodephrase;

    private List<String> redGuessWords;
    private List<String> blueGuessWords;

    private List<Integer> friendlyGuessesFromBlue;
    private List<Integer> friendlyGuessesFromRed;

    private List<Integer> oppositionGuessesFromBlue;
    private List<Integer> oppositionGuessesFromRed;

    private List<Player> awaitingPlayers;

    private Integer turnNumber;

    private Score score;

    public GameAggregateBuilder(GameAggregate game) {
        this.game = game;
    }

    public GameAggregateBuilder withPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public GameAggregateBuilder withAwaitingPlayers(List<Player> players) {
        this.awaitingPlayers = players;
        return this;
    }

    public GameAggregateBuilder withGameState(GameStatus status) {
        this.gameState = status;
        return this;
    }

    public GameAggregateBuilder withTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
        return this;
    }

    public GameAggregateBuilder withRedWordlist(WordList wordlist) {
        this.redWordlist = wordlist;
        return this;
    }

    public GameAggregateBuilder withRedTurnOrder(List<Player> players) {
        this.redTurnOrder = players;
        return this;
    }

    public GameAggregateBuilder withRedCodephrase(CodePhrase redCodephrase) {
        this.redCodephrase = redCodephrase;
        return this;
    }

    public GameAggregateBuilder withBlueWordlist(WordList wordlist) {
        this.blueWordlist = wordlist;
        return this;
    }

    public GameAggregateBuilder withBlueTurnOrder(List<Player> players) {
        this.blueTurnOrder = players;
        return this;
    }

    public GameAggregateBuilder withBlueCodephrase(CodePhrase redCodephrase) {
        this.blueCodephrase = redCodephrase;
        return this;
    }

    public GameAggregateBuilder withBlueGuessWords(List<String> guessWords) {
        this.blueGuessWords = guessWords;
        return this;
    }

    public GameAggregateBuilder withRedGuessWords(List<String> guessWords) {
        this.redGuessWords = guessWords;
        return this;
    }

    public GameAggregateBuilder withScore(Score score) {
        this.score = score;
        return this;
    }

    public GameAggregateBuilder withGuesses(List<Integer> guesses, Team team, Parity parity) {
        if(team == Team.RED_TEAM) {
            if(parity == Parity.OPPOSITION) {
                oppositionGuessesFromRed = guesses;
            } else if (parity == Parity.FRIENDLY) {
                friendlyGuessesFromRed = guesses;
            }
        }

        if(team == Team.BLUE_TEAM) {
            if(parity == Parity.OPPOSITION) {
                oppositionGuessesFromBlue = guesses;
            } else if (parity == Parity.FRIENDLY) {
                friendlyGuessesFromBlue = guesses;
            }
        }

        return this;
    }

    public GameAggregate build() {
        return new GameAggregate(
                players != null ? players : game.getPlayers(),
                gameState != null ? gameState : game.getGameState(),
                redWordlist != null ? redWordlist : game.getRedWordlist(),
                blueWordlist != null ? blueWordlist : game.getBlueWordlist(),
                redTurnOrder != null ? redTurnOrder : game.getRedTurnOrder(),
                blueTurnOrder != null ? blueTurnOrder : game.getBlueTurnOrder(),
                turnNumber != null ? turnNumber : game.getTurnNumber(),
                redCodephrase != null ? redCodephrase : game.getRedCodephrase(),
                blueCodephrase != null ? blueCodephrase : game.getBlueCodephrase(),
                redGuessWords != null ? redGuessWords : game.getRedGuessWords(),
                blueGuessWords != null ? blueGuessWords : game.getBlueGuessWords(),
                friendlyGuessesFromRed != null ? friendlyGuessesFromRed : game.getFriendlyGuessesFromRed(),
                friendlyGuessesFromBlue != null ? friendlyGuessesFromBlue : game.getFriendlyGuessesFromBlue(),
                oppositionGuessesFromRed != null ? oppositionGuessesFromRed : game.getOppositionGuessesFromRed(),
                oppositionGuessesFromBlue != null ? oppositionGuessesFromBlue : game.getOppositionGuessesFromBlue(),
                score != null ? score : game.getScore(),
                awaitingPlayers != null ? awaitingPlayers : game.getAwaitingPlayers()
        );
    }
}

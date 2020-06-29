package com.swamwithturtles.decrypto.stateMachine;

import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.CodePhrase;
import com.swamwithturtles.decrypto.model.GameStatus;
import com.swamwithturtles.decrypto.model.Parity;
import com.swamwithturtles.decrypto.model.Player;
import com.swamwithturtles.decrypto.model.Score;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.model.WordList;
import com.swamwithturtles.decrypto.model.event.AcknowledgeResults;
import com.swamwithturtles.decrypto.model.event.AllocatePlayer;
import com.swamwithturtles.decrypto.model.event.ArchivePublicGuesses;
import com.swamwithturtles.decrypto.model.event.AssignNewCodePhrases;
import com.swamwithturtles.decrypto.model.event.BeginTurn;
import com.swamwithturtles.decrypto.model.event.ClearDownModel;
import com.swamwithturtles.decrypto.model.event.EndGame;
import com.swamwithturtles.decrypto.model.event.GenerateWordLists;
import com.swamwithturtles.decrypto.model.event.MarkGuesses;
import com.swamwithturtles.decrypto.model.event.MoveGameOutOfWaiting;
import com.swamwithturtles.decrypto.model.event.ProvideGuess;
import com.swamwithturtles.decrypto.model.event.ProvideGuessWord;
import com.swamwithturtles.decrypto.model.event.RegisterPlayer;
import com.swamwithturtles.decrypto.model.event.SetTurnOrder;
import com.swamwithturtles.decrypto.model.event.StartTurn;
import com.swamwithturtles.decrypto.model.event.TransitionToAwaitingAcknowledgements;
import com.swamwithturtles.decrypto.model.event.TransitionToEndOfTurn;
import com.swamwithturtles.decrypto.model.event.MigrateToNewGuess;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameAggregate {

    private final List<Player> players;
    private final GameStatus gameState;

    private final WordList redWordlist;
    private final WordList blueWordlist;

    private final List<Player> redTurnOrder;
    private final List<Player> blueTurnOrder;

    private final CodePhrase redCodephrase;
    private final CodePhrase blueCodephrase;

    private final List<String> redGuessWords;
    private final List<String> blueGuessWords;

    private final List<Integer> friendlyGuessesFromBlue;
    private final List<Integer> friendlyGuessesFromRed;

    private final List<Integer> oppositionGuessesFromBlue;
    private final List<Integer> oppositionGuessesFromRed;

    private final Integer turnNumber;

    private final Score score;

    private List<Player> awaitingPlayers;

    public GameAggregate() {
        this.players = new ArrayList<>();
        this.gameState = GameStatus.WAITING;

        this.redWordlist = null;
        this.blueWordlist = null;

        this.redTurnOrder = null;
        this.blueTurnOrder = null;

        this.blueCodephrase = null;
        this.redCodephrase = null;

        this.redGuessWords = null;
        this.blueGuessWords = null;

        this.friendlyGuessesFromBlue = null;
        this.friendlyGuessesFromRed = null;
        this.oppositionGuessesFromBlue = null;
        this.oppositionGuessesFromRed = null;

        this.score = new Score(0, 0, 0, 0);

        this.awaitingPlayers = new ArrayList<>();

        this.turnNumber = -1;
    }

    protected GameAggregate(List<Player> players, GameStatus gameState, WordList redWordlist, WordList blueWordlist,
                            List<Player> redTurnOrder, List<Player> blueTurnOrder, Integer turnNumber,
                            CodePhrase redCodephrase, CodePhrase blueCodephrase, List<String> redGuessWords,
                            List<String> blueGuessWords, List<Integer> friendlyGuessesFromRed,
                            List<Integer> friendlyGuessesFromBlue, List<Integer> oppositionGuessesFromRed,
                            List<Integer> oppositionGuessesFromBlue, Score score, List<Player> awaitingPlayers) {
        this.players = players;
        this.gameState = gameState;
        this.redWordlist = redWordlist;
        this.blueWordlist = blueWordlist;
        this.redTurnOrder = redTurnOrder;
        this.blueTurnOrder = blueTurnOrder;
        this.blueCodephrase = blueCodephrase;
        this.redCodephrase = redCodephrase;
        this.turnNumber = turnNumber;
        this.redGuessWords = redGuessWords;
        this.blueGuessWords = blueGuessWords;
        this.friendlyGuessesFromRed = friendlyGuessesFromRed;
        this.friendlyGuessesFromBlue = friendlyGuessesFromBlue;
        this.oppositionGuessesFromRed = oppositionGuessesFromRed;
        this.oppositionGuessesFromBlue = oppositionGuessesFromBlue;
        this.score = score;
        this.awaitingPlayers = awaitingPlayers;
    }

    public List<String> getRedGuessWords() {
        return redGuessWords;
    }

    public List<String> getBlueGuessWords() {
        return blueGuessWords;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameStatus getGameState() {
        return gameState;
    }

    public WordList getRedWordlist() {
        return redWordlist;
    }

    public WordList getBlueWordlist() {
        return blueWordlist;
    }

    public List<Player> getRedTurnOrder() {
        return redTurnOrder;
    }

    public List<Player> getBlueTurnOrder() {
        return blueTurnOrder;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public CodePhrase getRedCodephrase() {
        return redCodephrase;
    }

    public CodePhrase getBlueCodephrase() {
        return blueCodephrase;
    }

    public Score getScore() { return score; }

    public List<Integer> getFriendlyGuessesFromBlue() {
        return friendlyGuessesFromBlue;
    }

    public List<Integer> getFriendlyGuessesFromRed() {
        return friendlyGuessesFromRed;
    }

    public List<Integer> getOppositionGuessesFromBlue() {
        return oppositionGuessesFromBlue;
    }

    public List<Integer> getOppositionGuessesFromRed() {
        return oppositionGuessesFromRed;
    }

    public GameAggregate fromEventStream(List<GameStateEvent> gameStateEvents) {
        if (gameStateEvents.isEmpty()) {
            return this;
        }

        GameStateEvent currentEvent = gameStateEvents.get(0);
        GameAggregate evolvedGameAggregate = this;

        if (currentEvent instanceof RegisterPlayer) {
            List<Player> newPlayerList = players;
            newPlayerList.add(new Player(((RegisterPlayer) currentEvent).getPlayerName()));
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withPlayers(newPlayerList)
                    .build();
        }

        if (currentEvent instanceof MoveGameOutOfWaiting) {
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withGameState(GameStatus.GENERATING)
                    .build();
        }

        if (currentEvent instanceof GenerateWordLists) {
            GenerateWordLists event = (GenerateWordLists) currentEvent;
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withRedWordlist(event.getTeamOneWordlist())
                    .withBlueWordlist(event.getTeamTwoWordlist())
                    .build();
        }

        if (currentEvent instanceof AllocatePlayer) {
            AllocatePlayer event = (AllocatePlayer) currentEvent;
            List<Player> newPlayerList = players;
            players.remove(event.getPlayer());
            Player newPlayer = new Player(event.getPlayer().getName(), event.getTeam());
            newPlayerList.add(newPlayer);

            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withPlayers(newPlayerList)
                    .build();
        }

        if (currentEvent instanceof SetTurnOrder) {
            SetTurnOrder event = (SetTurnOrder) currentEvent;

            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withRedTurnOrder(event.getRedTurnOrder())
                    .withBlueTurnOrder(event.getBlueTurnOrder())
                    .build();
        }

        if (currentEvent instanceof BeginTurn) {
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withGameState(GameStatus.BETWEEN_TURNS)
                    .build();
        }

        if (currentEvent instanceof AssignNewCodePhrases) {
            if (((AssignNewCodePhrases) currentEvent).getTeam() == Team.RED_TEAM) {
                evolvedGameAggregate = new GameAggregateBuilder(this)
                        .withRedCodephrase(((AssignNewCodePhrases) currentEvent).getCodePhrase())
                        .build();
            } else {
                evolvedGameAggregate = new GameAggregateBuilder(this)
                        .withBlueCodephrase(((AssignNewCodePhrases) currentEvent).getCodePhrase())
                        .build();
            }

        }

        if (currentEvent instanceof StartTurn) {
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withTurnNumber(turnNumber + 1)
                    .withGameState(GameStatus.AWAITING_GUESSWORDS)
                    .withAwaitingPlayers(players)
                    .build();
        }

        if (currentEvent instanceof ProvideGuessWord) {
            GameAggregateBuilder gameBuilder = new GameAggregateBuilder(this);

            if(((ProvideGuessWord) currentEvent).getTeam() == Team.RED_TEAM) {
                gameBuilder.withRedGuessWords(((ProvideGuessWord) currentEvent).getGuessWords());
            }

            if(((ProvideGuessWord) currentEvent).getTeam() == Team.BLUE_TEAM) {
                gameBuilder.withBlueGuessWords(((ProvideGuessWord) currentEvent).getGuessWords());
            }

            evolvedGameAggregate = gameBuilder.build();
        }


        if (currentEvent instanceof ProvideGuess) {
            ProvideGuess guessEvent = (ProvideGuess)currentEvent;

            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withGuesses(guessEvent.getGuesses(), guessEvent.getTeam(), guessEvent.getParity())
                    .build();
        }

        if (currentEvent instanceof MigrateToNewGuess) {
            MigrateToNewGuess migrateEvent = (MigrateToNewGuess)currentEvent;

            GameStatus newStatus;
            if(migrateEvent.getParity() == Parity.FRIENDLY) {
                if(migrateEvent.getTeam() == Team.RED_TEAM) {
                    newStatus = GameStatus.AWAITING_GUESSES_RED_FRIENDLY;
                } else {
                    newStatus = GameStatus.AWAITING_GUESSES_BLUE_FRIENDLY;
                }
            } else {
                if(migrateEvent.getTeam() == Team.RED_TEAM) {
                    newStatus = GameStatus.AWAITING_GUESSES_RED_OPPOSITION;
                } else {
                    newStatus = GameStatus.AWAITING_GUESSES_BLUE_OPPOSITION;
                }
            }

            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withGameState(newStatus)
                    .build();
        }

        if (currentEvent instanceof MarkGuesses) {
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withScore(((MarkGuesses) currentEvent).getScore())
                    .build();
        }

        if(currentEvent instanceof TransitionToEndOfTurn) {
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withGameState(GameStatus.END_OF_TURN_TRANSITION)
                    .build();
        }

        if(currentEvent instanceof ArchivePublicGuesses) {
            ArchivePublicGuesses event = (ArchivePublicGuesses) currentEvent;
            GameAggregateBuilder builder = new GameAggregateBuilder(this);

            if(event.getClueWord().getTeam().equals(Team.RED_TEAM)) {
                builder.withRedWordlist(
                        this.redWordlist.addPublicClues(event.getClueWord())
                );
            } else {
                builder.withBlueWordlist(
                        this.blueWordlist.addPublicClues(event.getClueWord())
                );
            }


            evolvedGameAggregate = builder.build();
        }

        if(currentEvent instanceof ClearDownModel) {
            evolvedGameAggregate = new GameAggregate(players, gameState, redWordlist, blueWordlist, redTurnOrder,
                    blueTurnOrder, turnNumber, null, null, null, null, null, null, null, null, score, awaitingPlayers);
        }

        if(currentEvent instanceof EndGame) {
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withGameState(GameStatus.END_OF_GAME)
                    .build();
        }

        if(currentEvent instanceof TransitionToAwaitingAcknowledgements) {
            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withGameState(GameStatus.AWAITING_ACKNOWLEDGEMENTS)
                    .build();
        }

        if(currentEvent instanceof AcknowledgeResults) {
            AcknowledgeResults ack = (AcknowledgeResults)currentEvent;

            evolvedGameAggregate = new GameAggregateBuilder(this)
                    .withAwaitingPlayers(
                            awaitingPlayers.stream().filter(p -> !p.getName().equals(ack.getPlayerName())).collect(Collectors.toList())
                    )
                    .build();
        }

        return evolvedGameAggregate.fromEventStream(gameStateEvents.subList(1, gameStateEvents.size()));


    }

    public Player getClueGiver(Team team) {
        if (team == Team.RED_TEAM) {
            return redTurnOrder.get(turnNumber % redTurnOrder.size());
        } else {
            return blueTurnOrder.get(turnNumber % blueTurnOrder.size());
        }
    }

    public List<Player> getAwaitingPlayers() {
        return awaitingPlayers;
    }
}

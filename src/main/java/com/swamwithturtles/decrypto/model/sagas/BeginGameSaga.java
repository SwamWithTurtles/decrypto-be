package com.swamwithturtles.decrypto.model.sagas;

import com.swamwithturtles.decrypto.model.event.AllocatePlayer;
import com.swamwithturtles.decrypto.model.event.BeginGame;
import com.swamwithturtles.decrypto.model.event.BeginTurn;
import com.swamwithturtles.decrypto.model.event.GenerateWordLists;
import com.swamwithturtles.decrypto.model.event.SetTurnOrder;
import com.swamwithturtles.decrypto.model.Player;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.model.WordList;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.data.GameStateMachine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BeginGameSaga {

    private GameStateMachine gameStateMachine;

    public BeginGameSaga(GameStateMachine gameStateMachine) {
        this.gameStateMachine = gameStateMachine;
    }

    public void generateNecessaryState(UUID gameId) {
        allocateTeams(gameId);
        generateWordlists(gameId);

        gameStateMachine.apply(gameId, new BeginGame());
    }

    private void allocateTeams(UUID gameId) {
        List<Player> playerList = gameStateMachine.getGameById(gameId).getPlayers();

        Integer sizeOfRedTeam = playerList.size() / 2;
        final List<Player> redTeam = pickNRandom(playerList, sizeOfRedTeam);

        final List<Player> blueTeam = new ArrayList<>(playerList);
        blueTeam.removeAll(redTeam);

        playerList.forEach(player -> {
            if(redTeam.contains(player)) {
                gameStateMachine.apply(gameId, new AllocatePlayer(player, Team.RED_TEAM));
            } else {
                gameStateMachine.apply(gameId, new AllocatePlayer(player, Team.BLUE_TEAM));
            }
        });

        List<Player> redTeamWithNames = redTeam.stream().map(player -> new Player(player.getName(), Team.RED_TEAM)).collect(Collectors.toList());
        List<Player> blueTeamWithNames = blueTeam.stream().map(player -> new Player(player.getName(), Team.BLUE_TEAM)).collect(Collectors.toList());

        Collections.shuffle(redTeam);
        Collections.shuffle(blueTeam);

        gameStateMachine.apply(gameId, new SetTurnOrder(redTeamWithNames, blueTeamWithNames));

    }

    private void generateWordlists(UUID gameId) {
        WordList teamOneWordlist = WordList.randomWordlist();
        WordList teamTwoWordlist = WordList.randomWordlist();

        GameStateEvent generateWordListsEvent = new GenerateWordLists(teamOneWordlist, teamTwoWordlist);

        gameStateMachine.apply(gameId, generateWordListsEvent);
    }

    private List<Player> pickNRandom(List<Player> lst, int n) {
        List<Player> copy = new LinkedList<Player>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

}

package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.model.exceptions.AllocatedPlayerNotInGameException;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.exceptions.PlayerAlreadyAllocatedToTeamException;
import com.swamwithturtles.decrypto.model.Player;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.UUID;

public class AllocatePlayer extends GameStateEvent {

    private final Player player;
    private final Team team;

    public AllocatePlayer(Player player, Team team) {

        this.player = player;
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }

    public boolean isValid(GameAggregate gameAggregate, UUID gameId) {
        if(gameAggregate.getGameState() != GameStatus.GENERATING) {
            throw new GameInvalidStateException(gameId);
        }

        if(gameAggregate.getPlayers().stream().noneMatch(p -> p.equals(player))) {
            throw new AllocatedPlayerNotInGameException(gameId, player.getName());
        }

        Player playerOnGameState = gameAggregate.getPlayers().stream()
                .filter(p -> p.equals(getPlayer()))
                .findFirst()
                .get();

        if(playerOnGameState.getTeam() != Team.UNALLOCATED) {
            throw new PlayerAlreadyAllocatedToTeamException(gameId, playerOnGameState.getName());
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "AllocatePlayer";
    }
}

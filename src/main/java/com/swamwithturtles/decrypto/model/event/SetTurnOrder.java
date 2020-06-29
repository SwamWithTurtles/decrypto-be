package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.model.exceptions.AllocatedPlayerNotInGameException;
import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.exceptions.PlayerInWrongTeamsTurnOrderException;
import com.swamwithturtles.decrypto.model.Player;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.List;
import java.util.UUID;

public class SetTurnOrder extends GameStateEvent {

    private final List<Player> redTurnOrder;
    private final List<Player> blueTurnOrder;

    public SetTurnOrder(List<Player> redTurnOrder, List<Player> blueTurnOrder) {
        this.redTurnOrder = redTurnOrder;
        this.blueTurnOrder = blueTurnOrder;
    }

    public List<Player> getRedTurnOrder() {
        return redTurnOrder;
    }

    public List<Player> getBlueTurnOrder() {
        return blueTurnOrder;
    }

    public boolean isValid(GameAggregate gameAggregate, UUID gameId) {
        if(gameAggregate.getGameState() != GameStatus.GENERATING) {
            throw new GameInvalidStateException(gameId);
        }

        if(blueTurnOrder.stream().anyMatch(p -> !gameAggregate.getPlayers().contains(p))) {
            Player impactedPlayer = blueTurnOrder.stream().filter(p -> !gameAggregate.getPlayers().contains(p)).findFirst().get();
            throw new AllocatedPlayerNotInGameException(gameId, impactedPlayer.getName());
        }

        if(redTurnOrder.stream().anyMatch(p -> !gameAggregate.getPlayers().contains(p))) {
            Player impactedPlayer = redTurnOrder.stream().filter(p -> !gameAggregate.getPlayers().contains(p)).findFirst().get();
            throw new AllocatedPlayerNotInGameException(gameId, impactedPlayer.getName());
        }

        if(blueTurnOrder.stream().anyMatch(player -> {
            Player matchingPlayer = gameAggregate.getPlayers().stream().filter(p -> p.getName().equals(player.getName())).findFirst().get();
            return matchingPlayer.getTeam() != Team.BLUE_TEAM;
        })) {
            throw new PlayerInWrongTeamsTurnOrderException(gameId, Team.BLUE_TEAM);
        }

        if(redTurnOrder.stream().anyMatch(player -> {
            Player matchingPlayer = gameAggregate.getPlayers().stream().filter(p -> p.getName().equals(player.getName())).findFirst().get();
            return matchingPlayer.getTeam() != Team.RED_TEAM;
        })) {
            throw new PlayerInWrongTeamsTurnOrderException(gameId, Team.RED_TEAM);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "SetTurnOrder";
    }
}

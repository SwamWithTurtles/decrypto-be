package com.swamwithturtles.decrypto.model.event;

import com.swamwithturtles.decrypto.model.exceptions.GameInvalidStateException;
import com.swamwithturtles.decrypto.model.CodePhrase;
import com.swamwithturtles.decrypto.model.Team;
import com.swamwithturtles.decrypto.stateMachine.GameAggregate;
import com.swamwithturtles.decrypto.data.GameStateEvent;
import com.swamwithturtles.decrypto.model.GameStatus;
import java.util.UUID;

public class AssignNewCodePhrases extends GameStateEvent {

    private final CodePhrase codePhrase;
    private final Team team;

    public CodePhrase getCodePhrase() {
        return codePhrase;
    }

    public Team getTeam() {
        return team;
    }

    public AssignNewCodePhrases(CodePhrase codePhrase, Team team) {
        this.codePhrase = codePhrase;
        this.team = team;
    }

    public boolean isValid(GameAggregate gameAggregate, UUID gameId) {
        if(gameAggregate.getGameState() != GameStatus.BETWEEN_TURNS) {
            throw new GameInvalidStateException(gameId);
        }

        if((team == Team.RED_TEAM && gameAggregate.getRedCodephrase() != null)
                || (team == Team.BLUE_TEAM && gameAggregate.getBlueCodephrase() != null)) {
            throw new GameInvalidStateException(gameId);
        }

        return true;
    }

    @Override
    public String getEventName() {
        return "AssignNewCodePhrases";
    }
}

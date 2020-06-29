package com.swamwithturtles.decrypto.model.exceptions;

import com.swamwithturtles.decrypto.model.Team;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerInWrongTeamsTurnOrderException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(PlayerInWrongTeamsTurnOrderException.class);

    public PlayerInWrongTeamsTurnOrderException(UUID gameId, Team teamName) {
        super(String.format("Problem %s %s", gameId.toString(), teamName.toString()));
        logger.warn(String.format("Problem %s %s", gameId.toString(), teamName.toString()));
    }
}

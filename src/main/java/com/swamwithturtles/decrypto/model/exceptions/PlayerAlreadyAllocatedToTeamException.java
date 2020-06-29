package com.swamwithturtles.decrypto.model.exceptions;

import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerAlreadyAllocatedToTeamException extends InvalidStateException {
    private static final Logger logger = LogManager.getLogger(PlayerAlreadyAllocatedToTeamException.class);

    public PlayerAlreadyAllocatedToTeamException(UUID gameId, String name) {
        super(String.format("Attempting to allocate player %s to a team when it alreayd is a member of one in game %s", name, gameId.toString()));
        logger.warn(String.format("Attempting to allocate player %s to a team when it alreayd is a member of one in game %s", name, gameId.toString()));
    }
}

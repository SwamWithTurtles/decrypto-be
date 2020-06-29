package com.swamwithturtles.decrypto.model;

import com.swamwithturtles.decrypto.stateMachine.GameAggregate;

public class Score {

    private final Integer redTeamInterceptions;
    private final Integer redTeamMiscommunications;
    private final Integer blueTeamInterceptions;
    private final Integer blueTeamMiscommunications;

    public Score(Integer redTeamInterceptions, Integer redTeamMiscommunications, Integer blueTeamInterceptions, Integer blueTeamMiscommunications) {
        this.redTeamInterceptions = redTeamInterceptions;
        this.redTeamMiscommunications = redTeamMiscommunications;
        this.blueTeamInterceptions = blueTeamInterceptions;
        this.blueTeamMiscommunications = blueTeamMiscommunications;
    }

    public Integer getRedTeamInterceptions() {
        return redTeamInterceptions;
    }

    public Integer getRedTeamMiscommunications() {
        return redTeamMiscommunications;
    }

    public Integer getBlueTeamInterceptions() {
        return blueTeamInterceptions;
    }

    public Integer getBlueTeamMiscommunications() {
        return blueTeamMiscommunications;
    }

    public static Score fromGameState(GameAggregate gameAggregate) {
        Integer redMiscommunications = gameAggregate.getScore().getRedTeamMiscommunications();
        Integer blueMiscommunications = gameAggregate.getScore().getBlueTeamMiscommunications();
        Integer redInterceptions = gameAggregate.getScore().getRedTeamInterceptions();
        Integer blueInterceptions = gameAggregate.getScore().getBlueTeamInterceptions();

        if(!gameAggregate.getRedCodephrase().getUnderlying().equals(gameAggregate.getFriendlyGuessesFromRed())) {
            redMiscommunications++;
        }
        if(!gameAggregate.getBlueCodephrase().getUnderlying().equals(gameAggregate.getFriendlyGuessesFromBlue())) {
            blueMiscommunications++;
        }

        if(gameAggregate.getTurnNumber() != 0) {
            if(gameAggregate.getBlueCodephrase().getUnderlying().equals(gameAggregate.getOppositionGuessesFromRed())) {
                redInterceptions++;
            }
            if(gameAggregate.getRedCodephrase().getUnderlying().equals(gameAggregate.getOppositionGuessesFromBlue())) {
                blueInterceptions++;
            }
        }

        return new Score(redInterceptions, redMiscommunications, blueInterceptions, blueMiscommunications);
    }

    public boolean hasVictoryCondition() {
        return redTeamMiscommunications >= 2 || redTeamInterceptions >= 2 || blueTeamInterceptions >= 2 || blueTeamMiscommunications >= 2;
    }
}

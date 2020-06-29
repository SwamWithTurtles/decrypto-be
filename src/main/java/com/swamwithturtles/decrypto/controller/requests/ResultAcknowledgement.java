package com.swamwithturtles.decrypto.controller.requests;

public class ResultAcknowledgement {

    private String playerName;

    public ResultAcknowledgement() {
    }

    public ResultAcknowledgement(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

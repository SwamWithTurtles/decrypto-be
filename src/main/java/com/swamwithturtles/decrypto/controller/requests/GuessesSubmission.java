package com.swamwithturtles.decrypto.controller.requests;

import com.swamwithturtles.decrypto.model.Parity;
import com.swamwithturtles.decrypto.model.Team;
import java.util.List;

public class GuessesSubmission {

    private List<Integer> guess;
    private String playerName;
    private Team team;
    private Parity parity;

    public GuessesSubmission() {
    }

    public GuessesSubmission(List<Integer> guess, String playerName, Team team, Parity parity) {
        this.guess = guess;
        this.playerName = playerName;
        this.team = team;
        this.parity = parity;
    }

    public List<Integer> getGuess() {
        return guess;
    }

    public void setGuess(List<Integer> guess) {
        this.guess = guess;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Parity getParity() {
        return parity;
    }

    public void setParity(Parity parity) {
        this.parity = parity;
    }
}

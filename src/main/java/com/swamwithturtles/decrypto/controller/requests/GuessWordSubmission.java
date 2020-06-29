package com.swamwithturtles.decrypto.controller.requests;

import com.swamwithturtles.decrypto.model.Team;
import java.util.List;

public class GuessWordSubmission {

    private String playerName;

    private List<String> guessWords;

    private Team team;

    public GuessWordSubmission() {
    }

    public GuessWordSubmission(String playerName, List<String> guessWords, Team team) {
        this.playerName = playerName;
        this.guessWords = guessWords;
        this.team = team;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getGuessWords() {
        return guessWords;
    }

    public Team getTeam() {
        return team;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGuessWords(List<String> guessWords) {
        this.guessWords = guessWords;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

package com.swamwithturtles.decrypto.model;

public class ClueWord {

    private final String word;
    private final Team team;
    private final Integer wordIndex;

    public ClueWord(String word, Team team, Integer wordIndex) {
        this.word = word;
        this.team = team;
        this.wordIndex = wordIndex;
    }

    public String getWord() {
        return word;
    }

    public Team getTeam() {
        return team;
    }

    public Integer getWordIndex() {
        return wordIndex;
    }
}

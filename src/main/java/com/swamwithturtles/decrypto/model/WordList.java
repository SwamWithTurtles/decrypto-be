package com.swamwithturtles.decrypto.model;

import com.swamwithturtles.decrypto.wordlist.WordGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordList {

    private final List<String> words;

    private final Map<Integer, Set<String>> publicClues;

    private WordList(List<String> wordList, Map<Integer, Set<String>> publicClues) {
        this.words = wordList;
        this.publicClues = publicClues;
    }

    public List<String> getWordList() {
        return words;
    }

    public Map<Integer, Set<String>> getPublicClues() {
        return publicClues;
    }

    public WordList addPublicClues(ClueWord clueWord) {
        Map<Integer, Set<String>> oldClues = publicClues;

        oldClues.get(clueWord.getWordIndex() - 1).add(clueWord.getWord());

        return new WordList(this.getWordList(), oldClues);
    }

    public static WordList randomWordlist() {

        HashMap<Integer, Set<String>> publicClues = new HashMap<>();
        publicClues.put(0, new HashSet<>());
        publicClues.put(1, new HashSet<>());
        publicClues.put(2, new HashSet<>());
        publicClues.put(3, new HashSet<>());

        return new WordList(new WordGenerator().getNewWordList(), publicClues);
    }
}

package com.swamwithturtles.decrypto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodePhrase {
    private final List<Integer> underlying;

    private CodePhrase(List<Integer> underlying) {
        this.underlying = underlying;
    }

    public List<Integer> getUnderlying() {
        return underlying;
    }

    public static CodePhrase random() {
        ArrayList<Integer> fullList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Collections.shuffle(fullList);

        fullList.remove(3);

        return new CodePhrase(fullList);
    }
}

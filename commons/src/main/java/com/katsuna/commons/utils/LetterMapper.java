package com.katsuna.commons.utils;

import java.util.LinkedHashMap;

public class LetterMapper {

    // Mapping result letters in latin - capital - non accented.
    private static final String LETTER_A = "A";
    private static final String LETTER_K = "K";

    private static LetterMapper ourInstance = new LetterMapper();
    private LinkedHashMap<String, String> map = new LinkedHashMap<>();
    private LetterMapper() {
        // For each language put the corresponding letter to the map in capital - non accented.
        // The following examples map the greek A and greek K to the latin ones.

        // Greek mappings
        map.put("Α", LETTER_A);
        map.put("Κ", LETTER_K);

        // German mappings ...
    }

    public static LetterMapper getInstance() {
        return ourInstance;
    }

    public String getLetter(String input) {
        String output = map.get(input);
        if (output == null) {
            output = input;
        }
        return output;
    }
}

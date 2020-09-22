/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.commons.utils;

import java.util.LinkedHashMap;

public class LetterMapper {

    // LetterMapper: all available groups
    // Mapping result letters in latin - capital - non accented.
    private static final String LETTER_A = "A";
    private static final String LETTER_B = "B";
    private static final String LETTER_E = "E";
    private static final String LETTER_H = "H";
    private static final String LETTER_I = "I";
    private static final String LETTER_K = "K";
    private static final String LETTER_M = "M";
    private static final String LETTER_N = "N";
    private static final String LETTER_O = "O";
    private static final String LETTER_P = "P";
    private static final String LETTER_S = "S";
    private static final String LETTER_T = "T";
    private static final String LETTER_X = "X";
    private static final String LETTER_Y = "Y";
    private static final String LETTER_Z = "Z";

    private static final LetterMapper ourInstance = new LetterMapper();
    private final LinkedHashMap<String, String> map = new LinkedHashMap<>();
    private final LinkedHashMap<String, String> lettersMapped = new LinkedHashMap<>();

    private LetterMapper() {
        // For each language put the corresponding letter to the map in capital - non accented.

        // Greek mappings
        map.put("Α", LETTER_A);
        map.put("Β", LETTER_B);
        map.put("Ε", LETTER_E);
        map.put("Η", LETTER_H);
        map.put("Ι", LETTER_I);
        map.put("Κ", LETTER_K);
        map.put("Μ", LETTER_M);
        map.put("Ν", LETTER_N);
        map.put("Ο", LETTER_O);
        map.put("Ρ", LETTER_P);
        map.put("Σ", LETTER_S);
        map.put("Τ", LETTER_T);
        map.put("Χ", LETTER_X);
        map.put("Υ", LETTER_Y);
        map.put("Ζ", LETTER_Z);

        // Cyrillic mappings (these characters are Cyrillic, even though they look like latin!)
        map.put("А", LETTER_A);
        map.put("В", LETTER_B);
        map.put("Е", LETTER_E);
        map.put("Н", LETTER_H);
        map.put("К", LETTER_K);
        map.put("М", LETTER_M);
        map.put("О", LETTER_O);
        map.put("Р", LETTER_P);
        map.put("Τ", LETTER_T);
        map.put("Х", LETTER_X);
        map.put("У", LETTER_Y);
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

    public String getLetterMapped(String input) {
        return lettersMapped.get(input);
    }

    public void putLetterMapped(String key, String value) {
        lettersMapped.put(key, value);
    }
}

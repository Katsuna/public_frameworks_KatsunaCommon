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

import java.text.Normalizer;

public class LetterNormalizer {

    public static String normalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String firstLetter = input.substring(0, 1);
        String output = LetterMapper.getInstance().getLetterMapped(firstLetter);
        if (output == null) {
            char firstLetterCapital = Character.toUpperCase(firstLetter.charAt(0));
            String firstLetterCapitalNoAccents = stripAccents(String.valueOf(firstLetterCapital));
            output = LetterMapper.getInstance().getLetter(firstLetterCapitalNoAccents);

            // store normalization result for future requests
            LetterMapper.getInstance().putLetterMapped(firstLetter, output);
        }

        return output;
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }


}
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
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

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TypefaceUtils {

    // thanks go to:
    // https://stackoverflow.com/a/46359954
    public static void applyCustomTypefaceSpan(TextView textView, Typeface typeface1,
                                               Typeface typeface2, String text, int index) {

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new CustomTypefaceSpan(typeface1), 0, index,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new CustomTypefaceSpan(typeface2), index + 1,
                text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spannableStringBuilder);

        if (textView instanceof ToggleButton) {
            ToggleButton toggleButton = (ToggleButton) textView;
            toggleButton.setTextOn(spannableStringBuilder);
            toggleButton.setTextOff(spannableStringBuilder);
        }
    }

}

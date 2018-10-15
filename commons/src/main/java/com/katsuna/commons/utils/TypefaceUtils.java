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

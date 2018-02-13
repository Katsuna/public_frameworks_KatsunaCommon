package com.katsuna.commons.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;

public class ColorAdjusterV2 {


    public static void adjustButtons(Context context, UserProfile profile,
                                     Button primaryButton, Button secondaryButton,
                                     TextView moreText) {
        adjustPrimaryButton(context, profile, primaryButton);
        adjustSecondaryButton(context, profile, secondaryButton);
        adjustMoreText(context, profile, moreText);
    }

    public static void adjustPrimaryButton(Context context, UserProfile profile, Button button) {
        if (button == null) return;

        int primaryColor2 = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2,
                profile.colorProfile);

        Shape.setRoundedBackground(button, primaryColor2);
    }

    public static void adjustSecondaryButton(Context context, UserProfile profile, Button button) {
        if (button == null) return;
        int primaryColor2 = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2,
                profile.colorProfile);

        int white = ContextCompat.getColor(context, R.color.common_white);
        Shape.setRoundedBorder(button, primaryColor2, white);
        button.setTextColor(primaryColor2);
    }

    public static void adjustMoreText(Context context, UserProfile profile, TextView textView) {
        if (textView == null) return;

        int primaryColor2 = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2,
                profile.colorProfile);
        textView.setTextColor(primaryColor2);
    }
}

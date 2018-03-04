package com.katsuna.commons.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
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

    public static void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawablesRelative()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static void adjustRadioButton(Context context, ColorProfile profile, RadioButton button) {
        // right hand default
        int buttonDrawableIndex = 2;
        adjustRadioButton(context, profile, button, buttonDrawableIndex, true);
    }

    public static void adjustRadioButton(Context context, ColorProfile profile, RadioButton button,
                                         int drawableIndex, boolean colorText) {
        int color = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2, profile);

        Drawable[] lHandDrawables = button.getCompoundDrawablesRelative();
        lHandDrawables[drawableIndex].setTint(color);

        if (colorText) {
            button.setTextColor(color);
        }
    }


}

package com.katsuna.commons.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;

public class ColorAdjuster {


    public static void adjustButtons(Context context, ColorProfile colorProfile,
                                     View primaryButtonContainer, Button primaryButton,
                                     View secondaryButtonContainer, Button secondaryButton) {
        if (colorProfile == ColorProfile.CONTRAST) {
            int color1 = ColorCalc.getColor(context, ColorProfileKey.ACCENT1_COLOR, colorProfile);
            Shape.setRoundedBackground(primaryButtonContainer, color1);

            int color2 = ColorCalc.getColor(context, ColorProfileKey.ACCENT2_COLOR,
                    colorProfile);
            Drawable[] drawables = primaryButton.getCompoundDrawables();
            DrawUtils.setColor(drawables[0], color2);
            primaryButton.setTextColor(color2);

            Shape.setRoundedBorder(secondaryButtonContainer, color1);

            Drawable[] messageButtondrawables = secondaryButton.getCompoundDrawables();
            DrawUtils.setColor(messageButtondrawables[0], color1);
            secondaryButton.setTextColor(color1);
        } else {
            int color1 = ColorCalc.getColor(context,
                    ColorProfileKey.ACCENT1_COLOR, colorProfile);
            Shape.setRoundedBackground(primaryButtonContainer, color1);
            int blackResId = ContextCompat.getColor(context, R.color.common_black);
            primaryButton.setTextColor(blackResId);

            int color2 = ColorCalc.getColor(context, ColorProfileKey.ACCENT2_COLOR,
                    colorProfile);
            Shape.setRoundedBorder(secondaryButtonContainer, color2);

            Drawable[] drawables = secondaryButton.getCompoundDrawables();
            DrawUtils.setColor(drawables[0], color2);
            secondaryButton.setTextColor(color2);
        }
    }

    public static void adjustButtons(Context context, ColorProfile colorProfile,
                                     Button primaryButton, Button secondaryButton) {

        int color1 = ColorCalc.getColor(context, ColorProfileKey.ACCENT1_COLOR, colorProfile);
        Shape.setRoundedBackground(primaryButton, color1);

        int color2 = ColorCalc.getColor(context, ColorProfileKey.ACCENT2_COLOR, colorProfile);

        if (secondaryButton != null) {
            Shape.setRoundedBorder(secondaryButton, color2);
        }

        int blackResId = ContextCompat.getColor(context, R.color.common_black);

        if (colorProfile == ColorProfile.CONTRAST) {
            Shape.setRoundedBackground(primaryButton, color1);

            Drawable[] drawables = primaryButton.getCompoundDrawablesRelative();
            primaryButton.setTextColor(color2);
            if (drawables[0] != null) {
                DrawUtils.setColor(drawables[0], color2);
            }

            if (secondaryButton != null) {
                Shape.setRoundedBorder(secondaryButton, color1);
                secondaryButton.setTextColor(color1);
                Drawable[] messageButtondrawables = secondaryButton.getCompoundDrawablesRelative();
                if (messageButtondrawables[0] != null) {
                    DrawUtils.setColor(messageButtondrawables[0], color1);
                }
            }
        } else {
            Shape.setRoundedBackground(primaryButton, color1);
            primaryButton.setTextColor(blackResId);

            if (secondaryButton != null) {
                Shape.setRoundedBorder(secondaryButton, color2);
                secondaryButton.setTextColor(color2);
                Drawable[] drawables = secondaryButton.getCompoundDrawablesRelative();
                if (drawables[0] != null) {
                    DrawUtils.setColor(drawables[0], color2);
                }
            }
        }
    }



    public static void adjustStartingLetter(Context context, ColorProfile colorProfile,
                                            TextView textView, boolean enabled) {
        int whiteResId = ContextCompat.getColor(context, R.color.common_white);
        int black87ResId = ContextCompat.getColor(context, R.color.common_black87);

        if (enabled) {
            int color1 = ColorCalc.getColor(context, ColorProfileKey.ACCENT1_COLOR, colorProfile);


            GradientDrawable circle = (GradientDrawable) ContextCompat.getDrawable(
                    context, R.drawable.circle_black_36dp);
            circle.setColor(color1);
            if (colorProfile == ColorProfile.CONTRAST) {
                textView.setTextColor(whiteResId);
            } else {
                textView.setTextColor(black87ResId);
            }
            textView.setBackground(circle);
        } else {
            textView.setBackground(null);
            textView.setTextColor(black87ResId);
        }

    }

}

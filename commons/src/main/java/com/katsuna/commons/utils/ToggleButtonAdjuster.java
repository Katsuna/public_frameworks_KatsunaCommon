package com.katsuna.commons.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.ToggleButton;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.UserProfile;

public class ToggleButtonAdjuster {

    public static void adjustToggleButton(Context context, ToggleButton toggleButton,
                                          Drawable background, UserProfile profile) {
        toggleButton.setBackground(clone(background));
        adjustToggleButtonText(context, toggleButton, profile);
    }

    public static void adjustToggleButtonText(Context context, ToggleButton toggleButton,
                                               UserProfile profile) {
        if (profile.colorProfile == ColorProfile.CONTRAST) {
            int white = ContextCompat.getColor(context, R.color.common_white);
            int black54 = ContextCompat.getColor(context, R.color.common_black54);

            int[][] states = new int[][]{
                    new int[]{android.R.attr.state_checked},
                    new int[]{-android.R.attr.state_checked}
            };

            int[] colors = new int[]{
                    white, black54
            };
            ColorStateList textColorSelector = new ColorStateList(states, colors);
            toggleButton.setTextColor(textColorSelector);
        } else {
            int textColor = ContextCompat.getColor(context, R.color.common_black54);
            toggleButton.setTextColor(textColor);
        }
    }

    public static void adjustToggleButtonIcon(Context context, ToggleButton toggleButton,
                                               int iconResId, UserProfile profile) {
        int white = ContextCompat.getColor(context, R.color.common_white);
        int black87 = ContextCompat.getColor(context, R.color.common_black87);
        int onColor;
        if (profile.colorProfile == ColorProfile.CONTRAST) {
            onColor = white;
        } else {
            onColor = black87;
        }

        Drawable icon = ContextCompat.getDrawable(context, iconResId);
        Drawable onDrawable = clone(icon);
        DrawUtils.setColor(onDrawable, onColor);

        Drawable offDrawable = clone(icon);
        DrawUtils.setColor(offDrawable, black87);

        StateListDrawable out = new StateListDrawable();
        out.addState(new int[]{android.R.attr.state_checked}, onDrawable);
        out.addState(new int[]{-android.R.attr.state_checked}, offDrawable);

        toggleButton.setCompoundDrawablesRelativeWithIntrinsicBounds(out, null, null, null);
    }

    private static Drawable clone(Drawable drawable) {
        return drawable.getConstantState().newDrawable();
    }
}

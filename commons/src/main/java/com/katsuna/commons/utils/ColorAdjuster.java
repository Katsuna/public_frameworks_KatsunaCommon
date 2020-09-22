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

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.UserProfile;

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

        adjustPrimaryButton(context, colorProfile, primaryButton);

        if (secondaryButton != null) {
            adjustSecondaryButton(context, colorProfile, secondaryButton);
        }
    }

    public static void adjustPrimaryButton(Context context, ColorProfile colorProfile,
                                             Button button) {
        int color1 = ColorCalc.getColor(context, ColorProfileKey.ACCENT1_COLOR, colorProfile);
        Shape.setRoundedBackground(button, color1);

        int color2 = ColorCalc.getColor(context, ColorProfileKey.ACCENT2_COLOR, colorProfile);

        int blackResId = ContextCompat.getColor(context, R.color.common_black);

        if (colorProfile == ColorProfile.CONTRAST) {
            Shape.setRoundedBackground(button, color1);

            Drawable[] drawables = button.getCompoundDrawables();
            button.setTextColor(color2);
            if (drawables[0] != null) {
                DrawUtils.setColor(drawables[0], color2);
            }
        } else {
            Shape.setRoundedBackground(button, color1);
            button.setTextColor(blackResId);
        }
    }

    public static void adjustSecondaryButton(Context context, ColorProfile colorProfile,
                                             Button button) {

        int color1 = ColorCalc.getColor(context, ColorProfileKey.ACCENT1_COLOR, colorProfile);
        int color2 = ColorCalc.getColor(context, ColorProfileKey.ACCENT2_COLOR, colorProfile);

        Shape.setRoundedBorder(button, color2);

        if (colorProfile == ColorProfile.CONTRAST) {
            Shape.setRoundedBorder(button, color1);
            button.setTextColor(color1);
            Drawable[] drawables = button.getCompoundDrawables();
            if (drawables[0] != null) {
                DrawUtils.setColor(drawables[0], color1);
            }
        } else {
            Shape.setRoundedBorder(button, color2);
            button.setTextColor(color2);
            Drawable[] drawables = button.getCompoundDrawables();
            if (drawables[0] != null) {
                DrawUtils.setColor(drawables[0], color2);
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

    public static void applyColor(Context context, ToggleButton button, ColorProfile profile) {

        int color1 = ColorCalc.getColor(context, ColorProfileKey.ACCENT1_COLOR, profile);
        int color2 = ColorCalc.getColor(context, ColorProfileKey.ACCENT2_COLOR, profile);

        StateListDrawable stateListDrawable = (StateListDrawable) button.getBackground();
        LayerDrawable enabledLayer = (LayerDrawable) getCheckedState(stateListDrawable);
        GradientDrawable mainBg = (GradientDrawable) enabledLayer.getDrawable(0);
        mainBg.setColor(color1);

        GradientDrawable bar = (GradientDrawable) enabledLayer.getDrawable(1);
        bar.setColor(color2);

        int grey600 = ContextCompat.getColor(context, R.color.common_grey600);
        Drawable[] drawables = button.getCompoundDrawables();

        if (profile == ColorProfile.CONTRAST) {
            button.setTextColor(ContextCompat.getColorStateList(context,
                    R.color.common_toggle_contrast_text_color));

            if (drawables[0] != null) {
                if (drawables[0] instanceof StateListDrawable) {
                    Drawable checkedDrawable = getCheckedState((StateListDrawable)drawables[0]);
                    DrawUtils.setColor(checkedDrawable, color2);
                }
            }
        } else {
            button.setTextColor(grey600);
            if (drawables[0] instanceof StateListDrawable) {
                Drawable checkedDrawable = getCheckedState((StateListDrawable)drawables[0]);
                DrawUtils.setColor(checkedDrawable, grey600);
            }
        }
    }

    private static Drawable getCheckedState(StateListDrawable input) {
        Drawable output = null;

        final DrawableContainer.DrawableContainerState containerState =
                (DrawableContainer.DrawableContainerState) input.getConstantState();
        final Drawable[] children = containerState.getChildren();
        if (children.length > 0) {
            output = children[0];
        }

        return output;
    }

    private static StateListDrawable makeSelector(Drawable drawable, int checkedColor, int color) {
        Drawable checkedDrawable =  drawable.getConstantState().newDrawable();
        checkedDrawable.setColorFilter(checkedColor, PorterDuff.Mode.SRC_ATOP);

        Drawable defaultDrawable =  drawable.getConstantState().newDrawable();
        defaultDrawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_checked}, checkedDrawable);
        res.addState(new int[]{}, defaultDrawable);
        return res;
    }

    public static void adjustRadioButton(Context context, UserProfile profile, RadioButton button) {
        // right hand default
        int buttonDrawableIndex = 2;
        if (!profile.isRightHanded) {
            buttonDrawableIndex = 0;
        }

        adjustRadioButton(context, profile, button, buttonDrawableIndex);
    }

    public static void adjustRadioButton(Context context, UserProfile profile, RadioButton button,
                                         int drawableIndex) {
        int color = ColorCalc.getColor(context, ColorProfileKey.ACCENT2_COLOR, profile.colorProfile);
        if(profile.colorProfile == ColorProfile.CONTRAST) {
            color = ColorCalc.getColor(context, ColorProfileKey.ACCENT1_COLOR, profile.colorProfile);
        }

        Drawable[] lHandDrawables = button.getCompoundDrawablesRelative();
        lHandDrawables[drawableIndex].setTint(color);
    }

}

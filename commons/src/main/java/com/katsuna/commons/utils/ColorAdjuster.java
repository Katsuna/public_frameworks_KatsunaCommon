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
import android.widget.TextView;
import android.widget.ToggleButton;

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

}

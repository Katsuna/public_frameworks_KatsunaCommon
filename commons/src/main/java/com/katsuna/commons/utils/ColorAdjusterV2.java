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
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.controls.KatsunaImageView;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;

import java.util.List;

public class ColorAdjusterV2 extends KatsunaAdjuster {

    public static void adjustButtons(Context context, UserProfile profile,
                                     Button primaryButton, Button secondaryButton) {
        adjustButtons(context, profile, primaryButton, secondaryButton, null);
    }

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

    public static void adjustEditText(Context context, ColorProfile profile, EditText editText) {
        int color = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2, profile);

        editText.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public static void applyColorProfile(Context context, ViewGroup viewGroup, UserProfile profile) {
        List<KatsunaImageView> katsunaImageViews = getKatsunaImageViews(viewGroup);
        for (KatsunaImageView v : katsunaImageViews) {
            adjustImageView(context, v, profile);
        }
    }

    private static void adjustImageView(Context context, KatsunaImageView v, UserProfile profile) {
        // adjust all katsuna icons
        ColorProfileKeyV2 key = v.getColorProfileKeyV2();
        if (key == null) {
            return;
        }

        int color = ColorCalcV2.getColor(context, key, profile.colorProfile);
        DrawUtils.setColor(v.getDrawable(), color);
    }

}

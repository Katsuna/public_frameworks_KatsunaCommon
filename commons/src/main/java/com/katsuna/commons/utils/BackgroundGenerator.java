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
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;

public class BackgroundGenerator {

    public static Drawable createToggleBg(Context context, UserProfile profile) {
        StateListDrawable out = new StateListDrawable();

        int bgColor;
        int lineColor;
        if (profile.colorProfile == ColorProfile.CONTRAST) {
            bgColor = ContextCompat.getColor(context, R.color.common_black);
            lineColor = ContextCompat.getColor(context, R.color.common_white);
        } else {
            bgColor = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_1,
                    profile.colorProfile);
            lineColor = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2,
                    profile.colorProfile);
        }
        Drawable onDrawable = getToggleBackground(context, bgColor, lineColor);

        bgColor = ContextCompat.getColor(context, R.color.common_white);
        lineColor = ContextCompat.getColor(context, R.color.common_grey300);
        Drawable offDrawable = getToggleBackground(context, bgColor, lineColor);

        out.addState(new int[]{android.R.attr.state_checked}, onDrawable);
        out.addState(new int[]{-android.R.attr.state_checked}, offDrawable);
        return out;
    }

    private static Drawable getToggleBackground(Context context, int bgColor, int lineColor) {
        // create bg drawable
        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.RECTANGLE);

        // set bg
        bg.setColor(bgColor);

        // set radius
        int radius = context.getResources().getDimensionPixelSize(R.dimen.common_toggle_radius);
        bg.setCornerRadius(radius);

        // set stroke
        int stroke = context.getResources().getDimensionPixelSize(R.dimen.common_toggle_stroke);
        int strokeColor = ContextCompat.getColor(context, R.color.common_grey300);
        bg.setStroke(stroke, strokeColor);

        // create bg drawable
        GradientDrawable line = new GradientDrawable();
        line.setShape(GradientDrawable.RECTANGLE);
        line.setColor(lineColor);

        // set size
        int width = context.getResources().getDimensionPixelSize(R.dimen.common_toggle_indicator_width);
        int height = context.getResources().getDimensionPixelSize(R.dimen.common_toggle_indicator_height);
        line.setSize(width, height);

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bg, line});
        layerDrawable.setLayerGravity(1, Gravity.CENTER | Gravity.BOTTOM);

        int bottomPadding = context.getResources().getDimensionPixelSize(R.dimen.common_8dp);
        layerDrawable.setLayerInsetBottom(1, bottomPadding);

        return layerDrawable;
    }

    public static Drawable createToggleBgV3(Context context, UserProfile profile,
                                            boolean strokeEnabled) {
        StateListDrawable out = new StateListDrawable();

        int bgColor;
        int lineColor;
        if (profile.colorProfile == ColorProfile.CONTRAST) {
            bgColor = ContextCompat.getColor(context, R.color.common_black);
            lineColor = ContextCompat.getColor(context, R.color.common_white);
        } else {
            bgColor = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_1,
                profile.colorProfile);
            lineColor = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2,
                profile.colorProfile);
        }
        Drawable onDrawable = getToggleBackgroundV3(context, bgColor, lineColor, strokeEnabled);

        bgColor = ContextCompat.getColor(context, R.color.common_white);
        lineColor = ContextCompat.getColor(context, R.color.common_grey300);
        Drawable offDrawable = getToggleBackgroundV3(context, bgColor, lineColor, strokeEnabled);

        out.addState(new int[]{android.R.attr.state_checked}, onDrawable);
        out.addState(new int[]{-android.R.attr.state_checked}, offDrawable);
        return out;
    }


    private static Drawable getToggleBackgroundV3(Context context, int bgColor, int lineColor,
                                                  boolean strokeEnabled) {
        // create bg drawable
        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.RECTANGLE);

        // set bg
        bg.setColor(bgColor);

        Resources res = context.getResources();
        // set stroke
        if (strokeEnabled) {
            int strokeWidth = res.getDimensionPixelSize(R.dimen.common_1dp);
            int strokeColor = res.getColor(R.color.common_grey100);
            bg.setStroke(strokeWidth, strokeColor);
        }

        // set radius
        int radius = res.getDimensionPixelSize(R.dimen.common_toggle_radius_v3);
        bg.setCornerRadius(radius);

        // create bg drawable
        GradientDrawable line = new GradientDrawable();
        line.setShape(GradientDrawable.RECTANGLE);
        line.setColor(lineColor);

        // set size
        int width = res.getDimensionPixelSize(R.dimen.common_toggle_indicator_width);
        int height = res.getDimensionPixelSize(R.dimen.common_toggle_indicator_height);
        line.setSize(width, height);

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bg, line});
        layerDrawable.setLayerGravity(1, Gravity.CENTER | Gravity.BOTTOM);

        int bottomPadding = res.getDimensionPixelSize(R.dimen.common_8dp);
        layerDrawable.setLayerInsetBottom(1, bottomPadding);

        return layerDrawable;
    }

}

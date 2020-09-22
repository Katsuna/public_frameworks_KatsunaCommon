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
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.SeekBar;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;

public class SeekBarUtils {

    public static void applyColor(Context context, SeekBar seekBar, ColorProfile profile) {

        int color = ColorCalc.getColor(context, ColorProfileKey.ACCENT2_COLOR, profile);

        if (profile == ColorProfile.CONTRAST) {
            color = ColorCalc.getColor(context, ColorProfileKey.ACCENT1_COLOR, profile);
        }

        DrawUtils.setColor(seekBar.getThumb(), color);

        LayerDrawable layerDrawable = (LayerDrawable) seekBar.getProgressDrawable();

        if (layerDrawable != null) {
            Drawable progressDrawable = layerDrawable.findDrawableByLayerId(android.R.id.progress);

            DrawUtils.setColor(progressDrawable, color);
        }
    }

    public static void adjustSeekbar(Context context, SeekBar seekBar, UserProfile profile,
                                     int thumbResId) {

        int color = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2,
            profile.colorProfile);

        LayerDrawable layerDrawable = (LayerDrawable) seekBar.getProgressDrawable();

        if (layerDrawable != null) {
            Drawable progressDrawable = layerDrawable.findDrawableByLayerId(android.R.id.progress);

            DrawUtils.setColor(progressDrawable, color);
        }

        // get thumb drawable
        Drawable thumbIcon = context.getDrawable(thumbResId);
        DrawUtils.setColor(thumbIcon, color);

        // get progress drawable
        int white = ContextCompat.getColor(context, R.color.common_white);
        int black12 = ContextCompat.getColor(context, R.color.common_black12);

        int size = context.getResources().getDimensionPixelSize(R.dimen.common_seekbar_thumb_size);
        int inset = context.getResources().getDimensionPixelSize(R.dimen.common_seekbar_thumb_inset);
        Drawable thumbBg = getThumbCircle(white, black12, size);

        // create and set layered progress drawable
        Drawable[] drawables = {thumbBg, thumbIcon};
        LayerDrawable thumbDrawable = new LayerDrawable(drawables);
        thumbDrawable.setLayerInset(1, inset, inset, inset, inset);

        seekBar.setThumb(thumbDrawable);
    }

    public static void adjustSeekbarV3(Context context, SeekBar seekBar, UserProfile profile,
                                       int thumbResId, int progressResId) {
        Resources res = context.getResources();


        int pColor1 = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_1,
            profile.colorProfile);

        int color = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2,
            profile.colorProfile);

        LayerDrawable layerDrawable = (LayerDrawable) context.getDrawable(progressResId);

        int progressRadius = res.getDimensionPixelSize(R.dimen.common_seekbar_radius_v3);

        Drawable progressDrawable = getProgressDrawableV3(pColor1, progressRadius);
        layerDrawable.setDrawableByLayerId(android.R.id.progress, progressDrawable);
        seekBar.setProgressDrawable(layerDrawable);


        // get thumb drawable
        Drawable thumbIcon = context.getDrawable(thumbResId);
        DrawUtils.setColor(thumbIcon, color);

        // get progress drawable
        int white = ContextCompat.getColor(context, R.color.common_white);
        int size = res.getDimensionPixelSize(R.dimen.common_seekbar_thumb_size);
        int inset = res.getDimensionPixelSize(R.dimen.common_seekbar_thumb_inset);

        Drawable thumbBg = getThumbCircleV3(white, size);

        // create and set layered progress drawable
        Drawable[] drawables = {thumbBg, thumbIcon};
        LayerDrawable thumbDrawable = new LayerDrawable(drawables);
        thumbDrawable.setLayerInset(1, inset, inset, inset, inset);

        seekBar.setThumb(thumbDrawable);
    }

    private static Drawable getThumbCircle(int color, int stroke, int size) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setSize(size, size);
        shape.setColor(color);
        shape.setStroke(2, stroke);
        return shape;
    }

    private static Drawable getThumbCircleV3(int color, int size) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setSize(size, size);
        shape.setColor(color);
        return shape;
    }

    private static Drawable getProgressDrawableV3(int color, int radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(radius);
        shape.setColor(color);
        return new ClipDrawable(shape, Gravity.START, ClipDrawable.HORIZONTAL);
    }

    private static Drawable getProgressDrawable(int color, int size, int radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(radius);
        shape.setSize(size, size);
        shape.setColor(color);
        return new ClipDrawable(shape, Gravity.START, ClipDrawable.HORIZONTAL);
    }

}

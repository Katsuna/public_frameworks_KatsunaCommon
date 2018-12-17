package com.katsuna.commons.utils;

import android.content.Context;
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

        int pColor1 = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_1,
            profile.colorProfile);

        int color = ColorCalcV2.getColor(context, ColorProfileKeyV2.PRIMARY_COLOR_2,
            profile.colorProfile);

        LayerDrawable layerDrawable = (LayerDrawable) context.getDrawable(progressResId);

        int progressRadius = context.getResources()
            .getDimensionPixelSize(R.dimen.common_seekbar_radius_v3);

        Drawable progressDrawable = getProgressDrawableV3(pColor1, progressRadius);
        layerDrawable.setDrawableByLayerId(android.R.id.progress, progressDrawable);
        seekBar.setProgressDrawable(layerDrawable);


        // get thumb drawable
        Drawable thumbIcon = context.getDrawable(thumbResId);
        DrawUtils.setColor(thumbIcon, color);

        // get progress drawable
        int white = ContextCompat.getColor(context, R.color.common_white);
        int transparent = ContextCompat.getColor(context, R.color.common_transparent);
        int black12 = ContextCompat.getColor(context, R.color.common_black12);

        int size = context.getResources().getDimensionPixelSize(R.dimen.common_seekbar_thumb_size);
        int inset = context.getResources().getDimensionPixelSize(R.dimen.common_seekbar_thumb_inset);
        Drawable thumbBg = getThumbCircleV3(white, size);

        // create and set layered progress drawable
        Drawable[] drawables = {thumbBg, thumbIcon};
        LayerDrawable thumbDrawable = new LayerDrawable(drawables);
        //thumbDrawable.setLayerInset(0, inset, inset, inset, inset);
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

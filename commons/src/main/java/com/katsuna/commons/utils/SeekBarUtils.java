package com.katsuna.commons.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.SeekBar;

import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;

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

}

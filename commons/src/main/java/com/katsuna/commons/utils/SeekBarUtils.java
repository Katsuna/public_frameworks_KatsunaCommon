package com.katsuna.commons.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.SeekBar;

public class SeekBarUtils {

    public static void applyColor(SeekBar seekBar, int color) {

        DrawUtils.setColorATOP(seekBar.getThumb(), color);

        LayerDrawable layerDrawable = (LayerDrawable) seekBar.getProgressDrawable();

        if (layerDrawable != null) {
            Drawable progressDrawable = layerDrawable.findDrawableByLayerId(android.R.id.progress);

            DrawUtils.setColorATOP(progressDrawable, color);
        }
    }

}

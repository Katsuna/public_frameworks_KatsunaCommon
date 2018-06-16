package com.katsuna.commons.utils;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

public class DrawUtils {

    public static void setColor(Drawable drawable, int color) {
        if (drawable == null) return;

        drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    public static void setDimensions(Drawable drawable, int width, int height) {
        if (drawable == null) return;

        drawable.setBounds(0, 0, width, height);
    }

}

package com.katsuna.commons.utils;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class Shape {

    public static void setRoundedBackground(View v, int backgroundColor) {
        Resources r = v.getResources();
        int resId = ResourcesUtils.getResourceIdByName(v.getContext().getPackageName(), "dimen",
                "common_corner_radius");
        float radius = r.getDimension(resId);

        setRoundedBackground(v, backgroundColor, radius);
    }

    public static void setRoundedBackground(View v, int backgroundColor, float radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius,
                radius});
        shape.setColor(backgroundColor);
        v.setBackground(shape);
    }
}

package com.katsuna.commons.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import com.katsuna.commons.R;

public class Shape {

    public static void setRoundedBackground(View v, int backgroundColor) {
        Resources r = v.getResources();
        float radius = r.getDimension(R.dimen.common_corner_radius);

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

    public static void setRoundedBorder(View v, int backgroundColor) {
        float radius = getCommonRadius(v.getContext());
        int borderStroke = getCommonStroke(v.getContext());

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius,
                radius});
        shape.setStroke(borderStroke, backgroundColor);
        v.setBackground(shape);
    }

    public static void setRoundedBorder(View v, int backgroundColor, int innerBackgroundColor) {
        float radius = getCommonRadius(v.getContext());
        int borderStroke = getCommonStroke(v.getContext());

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(innerBackgroundColor);
        shape.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius,
                radius});
        shape.setStroke(borderStroke, backgroundColor);
        v.setBackground(shape);
    }

    private static int getCommonStroke(Context context) {
        Resources r = context.getResources();
        return r.getDimensionPixelSize(R.dimen.common_border_stroke);
    }

    private static float getCommonRadius(Context context) {
        Resources r = context.getResources();
        return r.getDimension(R.dimen.common_corner_radius);
    }

    public static Drawable getCircle(int color, int size) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setSize(size, size);
        shape.setColor(color);
        return shape;
    }
}

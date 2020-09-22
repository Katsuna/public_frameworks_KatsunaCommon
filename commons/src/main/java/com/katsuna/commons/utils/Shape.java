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

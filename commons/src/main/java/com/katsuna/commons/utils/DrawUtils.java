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

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

public class DrawUtils {

    public static void setColor(Drawable drawable, int color) {
        if (drawable == null) return;

        drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    public static void setColor(Drawable drawable, int color, PorterDuff.Mode mode) {
        if (drawable == null) return;

        drawable.mutate().setColorFilter(color, mode);
    }

    public static void setDimensions(Drawable drawable, int width, int height) {
        if (drawable == null) return;

        drawable.setBounds(0, 0, width, height);
    }

}

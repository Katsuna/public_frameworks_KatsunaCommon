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

import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;

public class ScrollUtils {

    public static void focusOnView(final ScrollView scroll, final View v,
                                   final ScrollVertical vertical) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                int y = 0;

                switch (vertical) {
                    case TOP:
                        y = v.getTop();
                        break;
                    case CENTER:
                        y = ((v.getTop() + v.getBottom() - scroll.getHeight()) / 2);
                        break;
                    case BOTTOM:
                        y = v.getBottom();
                        break;
                }
                scroll.smoothScrollTo(0, y);
            }
        });
    }
}
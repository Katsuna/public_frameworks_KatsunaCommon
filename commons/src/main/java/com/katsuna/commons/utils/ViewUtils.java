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
import android.view.View;
import android.widget.ScrollView;

public class ViewUtils {

    private static String TAG = "ViewUtils";

    public static void verticalScrollToView(Context context, ScrollView scrollView, View target) {
        int screenHeight = DeviceUtils.getDisplayHeight(context);

        int scrollY = (getRelativeTop(target) - (screenHeight / 2)) + (target.getHeight() / 2);

        Log.d(TAG, "getRelativeTop=" + getRelativeTop(target) + " screenHeight=" + screenHeight
                + " target.getHeight() = " + target.getHeight() + "scrollY=" + scrollY);

        scrollView.smoothScrollTo(0, scrollY);
    }

    private static int getRelativeTop(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

}

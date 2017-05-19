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

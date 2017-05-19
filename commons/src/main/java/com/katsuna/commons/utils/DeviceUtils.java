package com.katsuna.commons.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceUtils {

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wManager.getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics;
    }

    public static int getDisplayHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }
}

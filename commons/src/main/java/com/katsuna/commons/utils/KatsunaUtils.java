package com.katsuna.commons.utils;

import android.content.Context;

public class KatsunaUtils {

    public static boolean katsunaOsDetected(Context context) {
        return (ProfileReader.getUserProfileFromKatsunaServices(context) != null);
    }

}

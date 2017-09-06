package com.katsuna.commons.utils;

import android.content.Context;

public class KatsunaUtils {

    private static final String KATSUNA_SERVICES_PACKAGE = "com.katsuna.services";

    public static boolean katsunaOsDetected(Context context) {
        return DeviceUtils.isPackageInstalled(context, KATSUNA_SERVICES_PACKAGE);
    }

}

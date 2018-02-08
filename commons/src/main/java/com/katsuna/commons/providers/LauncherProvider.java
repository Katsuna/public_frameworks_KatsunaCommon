package com.katsuna.commons.providers;

import android.net.Uri;

import com.katsuna.commons.BuildConfig;
import com.katsuna.commons.utils.KatsunaUtils;

public class LauncherProvider {
    private static final String AUTHORITY = ".services.datastore.providers.LauncherProvider";
    public static final String SCHEME = "content://";

    public static final String LAUNCHER_ACCESS = SCHEME + getAuthority() + "/launcher_access";
    public static final String LAUNCHER_ACCESS_BASE = LAUNCHER_ACCESS + "/";
    public static final Uri URI_LAUNCHER_ACCESS = Uri.parse(LAUNCHER_ACCESS);

    public static final String getAuthority() {
        String prefix = "com.katsuna";
        if (BuildConfig.BUILD_TYPE == KatsunaUtils.BUILD_TYPE_STAGING) {
            prefix += "." + KatsunaUtils.BUILD_TYPE_STAGING;
        }
        return prefix + AUTHORITY;
    }
}

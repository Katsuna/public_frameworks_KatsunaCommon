package com.katsuna.commons.providers;

import android.net.Uri;

import com.katsuna.commons.BuildConfig;
import com.katsuna.commons.utils.KatsunaUtils;

public class PreferenceProvider {
    private static final String AUTHORITY = ".services.datastore.providers.PreferenceProvider";
    public static final String SCHEME = "content://";

    public static final String PREFERENCE = SCHEME + getAuthority() + "/preference";
    public static final Uri URI_PREFERENCE = Uri.parse(PREFERENCE);

    public static final String getAuthority() {
        String prefix = "com.katsuna";
        if (BuildConfig.BUILD_TYPE == KatsunaUtils.BUILD_TYPE_STAGING) {
            prefix += "." + KatsunaUtils.BUILD_TYPE_STAGING;
        }
        return prefix + AUTHORITY;
    }
}

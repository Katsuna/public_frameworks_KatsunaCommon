package com.katsuna.commons.providers;

import android.net.Uri;

public class PreferenceProvider {
    // Warning dev-v2 branch specific. DONT MERGE WITH DEV!!!
    public static final String AUTHORITY = "com.katsuna.staging.services.datastore.providers.PreferenceProvider";
    public static final String SCHEME = "content://";

    public static final String PREFERENCE = SCHEME + AUTHORITY + "/preference";
    public static final Uri URI_PREFERENCE = Uri.parse(PREFERENCE);
}

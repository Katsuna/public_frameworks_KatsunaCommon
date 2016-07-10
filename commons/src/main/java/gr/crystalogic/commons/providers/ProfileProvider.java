package gr.crystalogic.commons.providers;

import android.net.Uri;

public class ProfileProvider {

    public static final long MAIN_PROFILE_ID = 1;

    public static final String AUTHORITY = "gr.crystalogic.datastore.providers.ProfileProvider";
    public static final String SCHEME = "content://";

    public static final String PROFILES = SCHEME + AUTHORITY + "/profile";
    public static final Uri URI_PROFILES = Uri.parse(PROFILES);
    public static final String PROFILE_BASE = PROFILES + "/";
}

package gr.crystalogic.commons.providers;

import android.net.Uri;

public class ErrorProvider {
    public static final String AUTHORITY = "gr.crystalogic.datastore.providers.ErrorProvider";
    public static final String SCHEME = "content://";

    public static final String ERRORS = SCHEME + AUTHORITY + "/error";
    public static final Uri URI_ERRORS = Uri.parse(ERRORS);
    public static final String ERROR_BASE = ERRORS + "/";
}

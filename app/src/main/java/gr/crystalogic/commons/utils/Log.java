package gr.crystalogic.commons.utils;

public class Log {

    protected static String getTag() {
        return "NONE SET";
    }

    protected static boolean isForceDebug() {
        return false;
    }

    private static final String TAG = getTag();
    private static final boolean FORCE_DEBUG = isForceDebug(); /* STOPSHIP if true */

    private static final boolean DEBUG = FORCE_DEBUG ||
            android.util.Log.isLoggable(TAG, android.util.Log.DEBUG);
    private static final boolean VERBOSE = FORCE_DEBUG ||
            android.util.Log.isLoggable(TAG, android.util.Log.VERBOSE);
    private static final String TAG_DELIMETER = " - ";

    public static void d(Object obj, String msg) {
        if (DEBUG) {
            android.util.Log.d(TAG, getPrefix(obj) + msg);
        }
    }

    public static void v(Object obj, String msg) {
        if (VERBOSE) {
            android.util.Log.v(TAG, getPrefix(obj) + msg);
        }
    }

    public static void e(Object obj, String msg, Exception e) {
        android.util.Log.e(TAG, getPrefix(obj) + msg, e);
    }

    public static void e(Object obj, String msg) {
        android.util.Log.e(TAG, getPrefix(obj) + msg);
    }

    public static void i(Object obj, String msg) {
        android.util.Log.i(TAG, getPrefix(obj) + msg);
    }

    public static void w(Object obj, String msg) {
        android.util.Log.w(TAG, getPrefix(obj) + msg);
    }

    public static void wtf(Object obj, String msg) {
        android.util.Log.wtf(TAG, getPrefix(obj) + msg);
    }

    private static String getPrefix(Object obj) {
        return (obj == null ? "" : (obj.getClass().getSimpleName() + TAG_DELIMETER));
    }


}
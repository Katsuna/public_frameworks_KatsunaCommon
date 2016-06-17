package gr.crystalogic.commons.utils;

public class Log {

    private static String TAG = "";
    private static boolean FORCE_DEBUG = false;

    public static void initialize(String tag, boolean forceDebug) {
        TAG = tag;
        FORCE_DEBUG = forceDebug;
    }

    private static final String TAG_DELIMETER = " - ";

    private static boolean debugEnabled() {
        return FORCE_DEBUG || android.util.Log.isLoggable(TAG, android.util.Log.DEBUG);
    }

    private static boolean verboseEnabled() {
        return FORCE_DEBUG || android.util.Log.isLoggable(TAG, android.util.Log.VERBOSE);
    }

    public static void d(Object obj, String msg) {
        if (debugEnabled()) {
            android.util.Log.d(TAG, getPrefix(obj) + msg);
        }
    }

    public static void v(Object obj, String msg) {
        if (verboseEnabled()) {
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
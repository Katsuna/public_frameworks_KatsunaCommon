/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.commons.utils;

import android.content.Context;

public class Log {

    private static Context mContext;
    private static String mAppName = "";
    private static boolean mForceDebug = false;

    public static void initialize(Context context, String appName, boolean forceDebug) {
        mContext = context;
        mAppName = appName;
        mForceDebug = forceDebug;
    }

    private static final String TAG_DELIMETER = " - ";

    private static boolean debugEnabled() {
        return mForceDebug || android.util.Log.isLoggable(mAppName, android.util.Log.DEBUG);
    }

    private static boolean verboseEnabled() {
        return mForceDebug || android.util.Log.isLoggable(mAppName, android.util.Log.VERBOSE);
    }

    public static void d(Object obj, String msg) {
        if (debugEnabled()) {
            android.util.Log.d(mAppName, getPrefix(obj) + msg);
        }
    }

    public static void v(Object obj, String msg) {
        if (verboseEnabled()) {
            android.util.Log.v(mAppName, getPrefix(obj) + msg);
        }
    }

    public static void e(Object obj, String msg, Exception e) {
        android.util.Log.e(mAppName, getPrefix(obj) + msg, e);
        ExceptionLogger.save(mContext, mAppName, msg + e.getMessage());
    }

    public static void e(Object obj, String msg) {
        android.util.Log.e(mAppName, getPrefix(obj) + msg);
        ExceptionLogger.save(mContext, mAppName, msg);
    }

    public static void i(Object obj, String msg) {
        android.util.Log.i(mAppName, getPrefix(obj) + msg);
    }

    public static void w(Object obj, String msg) {
        android.util.Log.w(mAppName, getPrefix(obj) + msg);
    }

    public static void wtf(Object obj, String msg) {
        android.util.Log.wtf(mAppName, getPrefix(obj) + msg);
    }

    private static String getPrefix(Object obj) {
        return (obj == null ? "" : (obj.getClass().getSimpleName() + TAG_DELIMETER));
    }
}
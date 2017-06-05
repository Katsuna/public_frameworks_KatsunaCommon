package com.katsuna.commons.utils;

import android.content.ContentValues;
import android.content.Context;

import com.katsuna.commons.entities.Preference;
import com.katsuna.commons.providers.PreferenceProvider;

public class PreferenceUtils {

    private static final String TAG = "PreferenceUtils";

    public static void updatePreference(Context context, Preference preference) {
        ContentValues values = new ContentValues();
        values.put(Preference.COL_VALUE, preference.getValue());
        values.put(Preference.COL_DESCR, preference.getDescr());

        String selection = Preference.COL_KEY + "=?";
        String[] selectionArgs = {preference.getKey()};

        int i = context.getContentResolver().update(PreferenceProvider.URI_PREFERENCE, values,
                selection, selectionArgs);
        if (i != 1) {
            android.util.Log.e(TAG, " updatePreference affected rows: " + i);
        }
    }

}

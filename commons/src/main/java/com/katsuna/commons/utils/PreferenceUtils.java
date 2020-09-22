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

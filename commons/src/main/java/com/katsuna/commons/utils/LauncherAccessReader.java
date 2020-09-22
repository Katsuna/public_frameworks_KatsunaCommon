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
import android.database.Cursor;

import com.katsuna.commons.entities.LauncherAccess;
import com.katsuna.commons.providers.LauncherProvider;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LauncherAccessReader {

    public static List<LauncherAccess> getLauncherStats(Context context) {
        List<LauncherAccess> lAccesses = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(LauncherProvider.URI_LAUNCHER_ACCESS,
                null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    LauncherAccess lAccess = new LauncherAccess();
                    lAccess.setId(cursor.getLong(cursor.getColumnIndex(LauncherAccess.COL_ID)));
                    lAccess.setComponent(cursor.getString(cursor.getColumnIndex(LauncherAccess.COL_COMPONENT)));
                    lAccess.setUser(cursor.getString(cursor.getColumnIndex(LauncherAccess.COL_USER)));
                    lAccess.setTime(cursor.getString(cursor.getColumnIndex(LauncherAccess.COL_TIME)));
                    lAccesses.add(lAccess);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return lAccesses;
    }

    public static HashMap<String, Integer> getLauncherStatsPerApp(Context context) {
        HashMap<String, Integer> output = new HashMap<>();
        String[] projection = new String[]{"count(" + LauncherAccess.COL_COMPONENT + ") as launch_count", LauncherAccess.COL_COMPONENT};
        String selection = LauncherAccess.COL_TIME + ">? GROUP BY (" + LauncherAccess.COL_COMPONENT + ")";
        DateTime oneMonthAgo = new DateTime().minusMonths(1);
        String[] selectionArgs = new String[]{ oneMonthAgo.toString() };
        Cursor cursor = context.getContentResolver().query(LauncherProvider.URI_LAUNCHER_ACCESS,
                projection, selection, selectionArgs, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int count = cursor.getInt(0);
                    String component = cursor.getString(1);
                    output.put(component, count);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return output;
    }

    public static void save(Context context, LauncherAccess lAccess) {
        ContentValues values = new ContentValues();
        values.put(LauncherAccess.COL_COMPONENT, lAccess.getComponent());
        values.put(LauncherAccess.COL_USER, lAccess.getUser());
        values.put(LauncherAccess.COL_TIME, new DateTime().toString());

        try {
            context.getContentResolver().insert(LauncherProvider.URI_LAUNCHER_ACCESS, values);
        } catch (Exception ex) {
            Log.w(LauncherAccessReader.class, ex.getMessage());
        }
    }

}

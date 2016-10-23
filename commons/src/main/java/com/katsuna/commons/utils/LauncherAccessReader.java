package com.katsuna.commons.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.katsuna.commons.entities.LauncherAccess;
import com.katsuna.commons.providers.LauncherProvider;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class LauncherAccessReader {

    public static List<LauncherAccess> getLauncherStats(Context context) {
        List<LauncherAccess> lAccesses = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(LauncherProvider.URI_LAUNCHER_ACCESS,
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                LauncherAccess lAccess = new LauncherAccess();
                lAccess.setId(cursor.getLong(cursor.getColumnIndex(LauncherAccess.COL_ID)));
                lAccess.setComponent(cursor.getString(cursor.getColumnIndex(LauncherAccess.COL_COMPONENT)));
                lAccess.setUser(cursor.getString(cursor.getColumnIndex(LauncherAccess.COL_USER)));
                lAccess.setTime(cursor.getString(cursor.getColumnIndex(LauncherAccess.COL_TIME)));
                lAccesses.add(lAccess);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lAccesses;
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

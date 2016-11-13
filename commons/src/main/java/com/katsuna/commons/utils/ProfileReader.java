package com.katsuna.commons.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.katsuna.commons.entities.Profile;
import com.katsuna.commons.providers.ProfileProvider;

public class ProfileReader {

    public static Profile getProfile(Context context) {
        return getProfileById(context, Profile.OPTICAL_SIZE_PROFILE);
    }

    public static Profile getProfileById(Context context, long profileId) {
        Profile profile = null;
        Cursor cursor = context.getContentResolver().query(
                Uri.withAppendedPath(ProfileProvider.URI_PROFILES, String.valueOf(profileId)),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                profile = new Profile();
                profile.setId(cursor.getLong(cursor.getColumnIndex(Profile.COL_ID)));
                profile.setType(cursor.getInt(cursor.getColumnIndex(Profile.COL_TYPE)));
                Log.d(context, "Error for content provider: " + profile);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return profile;
    }
}

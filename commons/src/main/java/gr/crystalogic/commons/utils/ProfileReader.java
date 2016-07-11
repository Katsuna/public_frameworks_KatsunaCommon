package gr.crystalogic.commons.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import gr.crystalogic.commons.entities.Profile;
import gr.crystalogic.commons.providers.ProfileProvider;

import static gr.crystalogic.commons.providers.ProfileProvider.MAIN_PROFILE_ID;

public class ProfileReader {

    public static Profile getProfile(Context context) {
        Profile profile = null;
        Cursor cursor = context.getContentResolver().query(
                Uri.withAppendedPath(ProfileProvider.URI_PROFILES, String.valueOf(MAIN_PROFILE_ID)),
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

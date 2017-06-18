package com.katsuna.commons.utils;

import android.content.Context;
import android.database.Cursor;

import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.Gender;
import com.katsuna.commons.entities.GenderInfo;
import com.katsuna.commons.entities.Notification;
import com.katsuna.commons.entities.Preference;
import com.katsuna.commons.entities.PreferenceKey;
import com.katsuna.commons.entities.SizeProfile;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.entities.UserProfileContainer;
import com.katsuna.commons.providers.PreferenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to retrieve Profile info given a context.
 */

public class ProfileReader {

    public static UserProfileContainer getKatsunaUserProfile(Context context) {
        UserProfile userProfileFromKatsunaServices = getUserProfileFromKatsunaServices(context);
        UserProfile userProfileFromAppSettings = getUserProfileFromAppSettings(context);

        return new UserProfileContainer(userProfileFromKatsunaServices, userProfileFromAppSettings);
    }

    public static UserProfile getUserProfileFromKatsunaServices(Context context) {
        UserProfile userProfile = null;

        try {
            Cursor cursor = context.getContentResolver().query(PreferenceProvider.URI_PREFERENCE, null,
                    null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        ArrayList<Preference> preferences = new ArrayList<>();
                        do {
                            Preference pref = new Preference();
                            pref.setId(cursor.getLong(Preference.COL_ID_INDEX));
                            pref.setKey(cursor.getString(Preference.COL_KEY_INDEX));
                            pref.setValue(cursor.getString(Preference.COL_VALUE_INDEX));
                            pref.setDescr(cursor.getString(Preference.COL_DESCR_INDEX));
                            preferences.add(pref);
                        } while (cursor.moveToNext());

                        userProfile = getUserProfileFromPreferences(preferences);
                    }
                } finally {
                    cursor.close();
                }
            }
        } catch (SecurityException se) {
            Log.d(context, "SecurityException! The app doesn't have the required permissions"
                    + " to access the Katsuna ContentProvider!");
        }

        return userProfile;
    }

    public static UserProfile getUserProfileFromAppSettings(Context context) {

        List<Preference> preferences = new ArrayList<>();

        String sizeValue = SettingsManager.readSetting(context, PreferenceKey.OPTICAL_SIZE_PROFILE,
                SizeProfile.AUTO.name());
        Preference opticalSize = new Preference(PreferenceKey.OPTICAL_SIZE_PROFILE, sizeValue);
        preferences.add(opticalSize);

        String value = SettingsManager.readSetting(context, PreferenceKey.COLOR_PROFILE,
                ColorProfile.AUTO.name());
        Preference opticalColor = new Preference(PreferenceKey.COLOR_PROFILE, value);
        preferences.add(opticalColor);

        Preference rightHand = new Preference(PreferenceKey.RIGHT_HAND,
                SettingsManager.readSetting(context, PreferenceKey.RIGHT_HAND, "true"));
        preferences.add(rightHand);

        return getUserProfileFromPreferences(preferences);
    }

    private static UserProfile getUserProfileFromPreferences(List<Preference> preferences) {
        UserProfile userProfile = new UserProfile();
        for (Preference pref : preferences) {
            switch (pref.getKey()) {
                case PreferenceKey.OPTICAL_SIZE_PROFILE: {
                    userProfile.opticalSizeProfile = SizeProfile.valueOf(pref.getValue());
                    break;
                }
                case PreferenceKey.COLOR_PROFILE: {
                    userProfile.colorProfile = ColorProfile.valueOf(pref.getValue());
                    break;
                }
                case PreferenceKey.RIGHT_HAND:
                    userProfile.handProfileAuto = "auto".equals(pref.getValue());
                    if (userProfile.handProfileAuto) {
                        userProfile.isRightHanded = true;
                    } else {
                        userProfile.isRightHanded = Boolean.parseBoolean(pref.getValue());
                    }
                    break;
                case PreferenceKey.NOTIFICATION:
                    userProfile.notification = Notification.valueOf(pref.getValue());
                    break;
                case PreferenceKey.AGE:
                    userProfile.age = pref.getValue();
                    break;
                case PreferenceKey.GENDER:
                    GenderInfo genderInfo = new GenderInfo();
                    genderInfo.gender = Gender.valueOf(pref.getValue());
                    genderInfo.descr = pref.getDescr();
                    userProfile.genderInfo = genderInfo;
                    break;
            }
        }
        return userProfile;
    }

}

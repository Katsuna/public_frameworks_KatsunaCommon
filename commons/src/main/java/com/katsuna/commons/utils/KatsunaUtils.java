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

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.KatsunaApp;

import java.util.ArrayList;
import java.util.List;

public class KatsunaUtils {

    public static final String BUILD_TYPE_STAGING = "staging";

    public static final String KATSUNA_LAUNCHER_PACKAGE = "com.katsuna.launcher";
    public static final String KATSUNA_CONTACTS_PACKAGE = "com.katsuna.contacts";
    public static final String KATSUNA_CONTACTS_STAGING_PACKAGE = KATSUNA_CONTACTS_PACKAGE + "." +
            BUILD_TYPE_STAGING;
    public static final String KATSUNA_MESSAGES_PACKAGE = "com.katsuna.messages";
    public static final String KATSUNA_CALLS_PACKAGE = "com.katsuna.calls";
    public static final String KATSUNA_KEYBOARD_PACKAGE = "com.katsuna.keyboard";
    public static final String KATSUNA_HOMESCREEN_WIDGET_PACKAGE = "com.katsuna.widgets";
    public static final String KATSUNA_CLOCK_PACKAGE = "com.katsuna.clock";
    public static final String KATSUNA_CALENDAR_PACKAGE = "com.katsuna.calendar";
    public static final String KATSUNA_INFOSERVICES_PACKAGE = "com.katsuna.infoservices";

    public static final String PROP_KATSUNA_VERSION = "ro.katsuna.version";

    static Boolean cachedKatsunaOsDetected = null;

    public static boolean katsunaOsDetected() {
        if (cachedKatsunaOsDetected == null) {
            cachedKatsunaOsDetected = !DeviceUtils.getProp(PROP_KATSUNA_VERSION).isEmpty();
        }
        return cachedKatsunaOsDetected;
    }

    public static List<KatsunaApp> getKatsunaApps(Context context) {
        List<KatsunaApp> apps = new ArrayList<>();

        apps.add(new KatsunaApp(context.getString(R.string.common_katsuna_contacts_app), KATSUNA_CONTACTS_PACKAGE,
            R.mipmap.ic_contacts_launcher));
        apps.add(new KatsunaApp(context.getString(R.string.common_katsuna_messages_app), KATSUNA_MESSAGES_PACKAGE,
            R.mipmap.ic_messages_launcher));
        apps.add(new KatsunaApp(context.getString(R.string.common_katsuna_calls_app), KATSUNA_CALLS_PACKAGE, R.mipmap.ic_calls_launcher));
        apps.add(new KatsunaApp(context.getString(R.string.common_katsuna_keyboard_app), KATSUNA_KEYBOARD_PACKAGE,
            R.mipmap.ic_keyboard_launcher));
        apps.add(new KatsunaApp(context.getString(R.string.common_katsuna_homescreen_widget), KATSUNA_HOMESCREEN_WIDGET_PACKAGE,
            R.mipmap.common_widgets_icon));
        apps.add(new KatsunaApp(context.getString(R.string.common_katsuna_clock),
            KATSUNA_CLOCK_PACKAGE, R.mipmap.ic_clock_launcher));
        apps.add(new KatsunaApp(context.getString(R.string.common_katsuna_calendar_app),
            KATSUNA_CALENDAR_PACKAGE, R.mipmap.ic_calendar_launcher));

        return apps;
    }

    public static void goToGooglePlay(Context context, String packageName) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +
                    packageName));
            context.startActivity(i);
        } catch (ActivityNotFoundException e) {
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
            context.startActivity(i);
        }
    }

}

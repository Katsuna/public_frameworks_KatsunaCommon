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
package com.katsuna.commons.notifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.ActivityCompat;


/**
 * Created by cmitatakis on 4/20/2017.
 */

public class CallActivity extends Activity {

    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String NUMBER = "NUMBER";

    int MY_PERMISSIONS_REQUEST_CALL_PHONE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String callNumber = getIntent().getStringExtra(NUMBER);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

            return;
        }

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+callNumber));
        startActivity(callIntent);


        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(getIntent().getIntExtra(NOTIFICATION_ID, -1));
        finish();
    }



    public static PendingIntent getCallIntent(int notificationId, String number, Context context) {
        Intent intent = new Intent(context, CallActivity.class);
        intent.putExtra(NOTIFICATION_ID, notificationId);
        intent.putExtra(NUMBER, number);
        PendingIntent dismissIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return dismissIntent;
    }


}

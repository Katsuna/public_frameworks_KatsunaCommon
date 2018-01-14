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

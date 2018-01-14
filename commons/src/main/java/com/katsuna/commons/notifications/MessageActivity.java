package com.katsuna.commons.notifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by cmitatakis on 06/06/2017.
 */

public class MessageActivity  extends Activity {

    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String NUMBER = "NUMBER";

    int MY_PERMISSIONS_REQUEST_CALL_PHONE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String callNumber = getIntent().getStringExtra(NUMBER);


        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + callNumber));
        startActivity(sendIntent);



        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(getIntent().getIntExtra(NOTIFICATION_ID, -1));
        finish();
    }



    public static PendingIntent getMessageIntent(int notificationId, String number, Context context) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(NOTIFICATION_ID, notificationId);
        intent.putExtra(NUMBER, number);
        PendingIntent dismissIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return dismissIntent;
    }


}

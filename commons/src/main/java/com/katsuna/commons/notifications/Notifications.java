package com.katsuna.commons.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.utils.ProfileReader;

import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by cmitatakis on 4/20/2017.
 */

public class Notifications {


    public static void callNotification(Context context , String text, String number)
    {

        UserProfile userProfile = ProfileReader.getUserProfileFromKatsunaServices(context);

        if(userProfile.notification.equals(com.katsuna.commons.entities.Notification.ON)) {


            int notificationId = new Random().nextInt();

            PendingIntent call = CallActivity.getCallIntent(notificationId, number, context.getApplicationContext());
            PendingIntent sms = MessageActivity.getMessageIntent(notificationId, number, context.getApplicationContext());


            PendingIntent dismissIntent = NotificationActivity.getDismissIntent(notificationId, context.getApplicationContext());

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.common_ic_call_black_24dp, context.getString(R.string.common_call_notification_call), call).build();
            //  NotificationCompat.Action message = new NotificationCompat.Action.Builder(R.drawable.common_ic_message_black_24dp,"Message", sms).build();

            NotificationCompat.Action remove = new NotificationCompat.Action.Builder(R.drawable.common_notification_close_button, context.getString(R.string.common_call_notification_cancel), dismissIntent).build();

//            Notification noti = new NotificationCompat.Builder(context)
//                    .setSmallIcon(R.drawable.common_ic_call_black_24dp)
//                    .setContentText(subject)
//                    .setContentTitle(context.getString(R.string.call_notification_title))
//                    .addAction(action)
//                    //     .addAction(message)
//                    .addAction(remove)
//                    .build();

            Notification n  = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.common_ic_call_black_24dp)
                    .setCustomBigContentView(getCallNotificationView(context, number, call, dismissIntent)).build();



            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            n.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(notificationId, n);
        }


    }

    public static void textNotification(Context context)
    {

        UserProfile userProfile = ProfileReader.getUserProfileFromKatsunaServices(context);

        if(userProfile.notification.equals(com.katsuna.commons.entities.Notification.ON)) {


            int notificationId = new Random().nextInt();
            PendingIntent dismissIntent = NotificationActivity.getDismissIntent(notificationId, context.getApplicationContext());

//            Notification noti = new Notification.Builder(context)
//                    .setContentTitle(title)
//                    .setContentText(subject).setSmallIcon(R.mipmap.ic_notifications_black_24dp)
//                    .build();
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//            noti.flags |= Notification.FLAG_AUTO_CANCEL;
//            notificationManager.notify(notificationId, noti);


            Notification n  = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setCustomBigContentView(getTextNotificationView(context, dismissIntent)).build();



            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            n.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(notificationId, n);

        }


    }

    private static RemoteViews getCallNotificationView(Context context, String text, PendingIntent call, PendingIntent dismissIntent) {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews notificationView = new RemoteViews(
                context.getPackageName(),
                R.layout.common_call_notification
        );

        // Locate and set the Image into customnotificationtext.xml ImageViews
        notificationView.setImageViewResource(
                R.id.call_image,
                R.drawable.common_ic_call_black_24dp);

        // Locate and set the Text into customnotificationtext.xml TextViews
        notificationView.setTextViewText(R.id.call_title, context.getString(R.string.common_call_notification_title));
        notificationView.setTextViewText(R.id.call_text, context.getString(R.string.common_call_notification_text) + " " + text);

        notificationView.setOnClickPendingIntent(R.id.call_action_button,
                call);

        notificationView.setOnClickPendingIntent(R.id.call_cancel_button,
                dismissIntent);

        return notificationView;
    }

    private static RemoteViews getTextNotificationView(Context context, PendingIntent dismissIntent) {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews notificationView = new RemoteViews(
                context.getPackageName(),
                R.layout.common_text_notification
        );

        // Locate and set the Image into customnotificationtext.xml ImageViews
        notificationView.setImageViewResource(
                R.id.text_notification_image,
                R.drawable.ic_notifications_black_24dp);

        // Locate and set the Text into customnotificationtext.xml TextViews
        notificationView.setTextViewText(R.id.text_notification_title, context.getString(R.string.common_text_notification_title));
        notificationView.setTextViewText(R.id.text_notification_text, context.getString(R.string.common_text_notification_text) );

        notificationView.setOnClickPendingIntent(R.id.text_notification_action_button,
                dismissIntent);



        return notificationView;
    }


}

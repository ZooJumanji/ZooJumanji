package com.example.hb.zoojumanji;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by hb on 13/06/2016.
 */
public class JumanjiNotification {

    private static int DEFAULT_ID = 1234567890;

    public static void make(Context context, String message) {
        make(context, message, DEFAULT_ID);
    }

    public static void make(Context context, String message, int id) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationBuilder.setContentTitle("Jumanji notification");
        notificationBuilder.setContentText(message);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notificationBuilder.build());
    }
}

package com.texastech.talk.notification;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.texastech.talk.MainActivity;
import com.texastech.talk.R;

public class AlarmReceiver extends BroadcastReceiver {
    public static String CHANNEL_ID = "AlarmReceiver.DailyNotification";
    public static String ASK_MOOD_INTENT_PARAM = "SHOW_USER_MOOD_DIALOG";
    private static int mNotificationId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra(ASK_MOOD_INTENT_PARAM, true);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_intro_slide1)
                .setContentTitle("How are you feeling?")
                .setContentText("Hit the notification to tell me how you're feeling")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationMgr = NotificationManagerCompat.from(context);
        notificationMgr.notify(mNotificationId++, builder.build());
    }
}

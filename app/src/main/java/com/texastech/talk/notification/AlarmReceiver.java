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
    /**
     * Listens for intents being broadcasted from the system's alarm
     * saying that a specified time has been reached. When this time has
     * been reached, the app will create a notification for the user
     * asking them to enter their current mood at the time.
     */
    private int mNotificationId = 0x1;
    NotificationCompat.Builder mBuilder = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        /**
         * Called when the broadcast is received from the alarm. Creates
         * the notification for the user to enter their mood.
         */
        if (mBuilder == null) {
            Intent notifyIntent = new Intent(context, MainActivity.class);
            notifyIntent.putExtra(MainActivity.QUERY_MOOD_PARAMETER, true);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder = new NotificationCompat.Builder(
                    context, MainActivity.NOTIFICATION_CHANNEL_ID);
            mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);
            mBuilder.setContentTitle("How are you feeling?");
            mBuilder.setContentText("Hit the notification to tell me how you're feeling");
            mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setAutoCancel(true);
        }

        NotificationManagerCompat notificationMgr = NotificationManagerCompat.from(context);
        notificationMgr.notify(mNotificationId++, mBuilder.build());
    }
}
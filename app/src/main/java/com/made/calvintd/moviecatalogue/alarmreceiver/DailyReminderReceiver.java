package com.made.calvintd.moviecatalogue.alarmreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.made.calvintd.moviecatalogue.R;

public class DailyReminderReceiver extends BroadcastReceiver {
    final long[] vibrationPattern = new long[]{1000, 1000, 1000, 1000, 1000};

    @Override
    public void onReceive(Context context, Intent intent) {
        final int DAILY_NOTIFICATION_ID = Integer.valueOf(context.getResources().getString(R.string.reminder_notification_daily_id));
        final String DAILY_NOTIFICATION_CHANNEL_ID = context.getResources().getString(R.string.reminder_notification_daily_channel_id);
        final String DAILY_NOTIFICATION_CHANNEL_NAME = context.getResources().getString(R.string.reminder_notification_daily_channel_name);
        final String DAILY_NOTIFICATION_TITLE = context.getResources().getString(R.string.reminder_notification_daily_title);
        final String DAILY_NOTIFICATION_MESSAGE = context.getResources().getString(R.string.reminder_notification_daily_message);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri reminderSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, DAILY_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_movie_black_36dp)
                .setContentTitle(DAILY_NOTIFICATION_TITLE)
                .setContentText(DAILY_NOTIFICATION_MESSAGE)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(vibrationPattern)
                .setSound(reminderSound);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(DAILY_NOTIFICATION_CHANNEL_ID, DAILY_NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(vibrationPattern);
            builder.setChannelId(DAILY_NOTIFICATION_CHANNEL_ID);
            if(notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Notification notification = builder.build();
        if(notificationManager != null) {
            notificationManager.notify(DAILY_NOTIFICATION_ID, notification);
        }
    }
}

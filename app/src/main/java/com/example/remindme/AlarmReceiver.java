package com.example.remindme;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String REMINDER_TITLE = "REMINDER_TITLE";
    public static final String REMINDER_NOTES = "REMINDER_NOTES";

    @Override
    public void onReceive(Context context, Intent intent) {

        String reminderTitle = intent.getStringExtra(REMINDER_TITLE);
        String reminderNotes = intent.getStringExtra(REMINDER_NOTES);

        Intent intentAction = new Intent(context, ClickedReminderActivity.class);
        // Can't get the info received from AddReminderActivity to send from here to ClickedReminderActivity
        intentAction.putExtra("REMINDER_TITLE", reminderTitle);
        intentAction.putExtra("REMINDER_NOTES", reminderNotes);
        // ^^^^
        PendingIntent pi = PendingIntent.getActivity(context, 0, intentAction, 0);
        String channelId = "Channel_id";

        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.clock)
                .setTicker("Reminder!")
                .setWhen(new Date().getTime())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText(reminderTitle)
                .setContentIntent(pi)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(1, notification);
    }
}

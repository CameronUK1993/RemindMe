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
import androidx.core.app.TaskStackBuilder;

import com.example.remindme.models.Reminder;
import com.example.remindme.utility.RemindersRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String REMINDER_TITLE = "REMINDER_TITLE";
    public static final String REMINDER_ID = "REMINDER_ID";



    private static final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: " + intent.getStringExtra(REMINDER_TITLE));
        String id = intent.getStringExtra(REMINDER_ID);
        Log.d(TAG, "onReceive: " + id);
        RemindersRepository instance = RemindersRepository.getInstance();

        Log.i("Logging -- Func_s", String.format("reminder %s", id));

        String reminderTitle = intent.getStringExtra(REMINDER_TITLE);

        Intent intentAction = new Intent(context, ClickedReminderActivity.class);
        intentAction.putExtra(ClickedReminderActivity.ARG_REMINDER_ID, id);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(intentAction);
        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

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

package com.example.johnmunyi.getupandwalk;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

/**
 * Created by johnmunyi on 10/27/17.
 */

public class NotificationFlash extends AppCompatActivity {
    public void showNotification()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
            builder.setContentTitle("Get up and Walk");
            builder.setContentText("Been seated for too long time has expired");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, 0);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }
}

package com.example.johnmunyi.getupandwalk;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.location.ActivityRecognition;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by johnmunyi on 10/26/17.
 */

public class Tracking extends AsyncTask<String, String, String> {

    private String resp;
    private Context context;

    Tracking(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params){
        try
        {
            int trackedTime = Integer.parseInt(params[0])*1000;

            while (trackedTime > 0)
            {
                if (ActivityRecognizedService.currentActivity == "Still")
                {
                    Thread.sleep(trackedTime);
                    resp = "Slept for " + params[0] + "seconds";
                    runNotification();
                }else{
                    Thread.sleep(trackedTime);
                }

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            resp = e.getMessage();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    protected void runNotification()
    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        builder.setContentTitle("Get up and Walk");
        builder.setContentText("Been seated for too long time has expired");
        builder.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,intent, 0);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
        System.out.println(resp);
        builder.setVibrate(new long[] {1000, 1000, 1000});
    }
}

package com.example.johnmunyi.getupandwalk;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

/**
 * Created by johnmunyi on 10/28/17.
 */

public class ActivityRecognizedService extends IntentService {

    static String currentActivity;
    public ActivityRecognizedService()
    {
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name)
    {
        super(name);
    }

    protected void onHandleIntent(Intent intent)
    {
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getProbableActivities());
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for( DetectedActivity activity : probableActivities ) {
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    if (activity.getConfidence() >= 75){
                        currentActivity = "In Vehicle";
                    }
                    Log.e( "ActivityRecogition", "In Vehicle: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    if (activity.getConfidence() >= 75){
                        currentActivity = "On Bicycle";
                    }
                    Log.e( "ActivityRecogition", "On Bicycle: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    if (activity.getConfidence() >= 75){
                        currentActivity = "On Foot";
                    }
                    Log.e( "ActivityRecogition", "On Foot: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.RUNNING: {
                    if (activity.getConfidence() >= 75) {
                        currentActivity = "Running";
                    }
                    Log.e( "ActivityRecogition", "Running: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.STILL: {
                    if(activity.getConfidence() >= 75){
                        currentActivity = "Still";
                    }
                    Log.e( "ActivityRecogition", "Still: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.TILTING: {
                    if( activity.getConfidence() == 100){
                        currentActivity = "Tilting";
                    }
                    Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.e( "ActivityRecogition", "Walking: " + activity.getConfidence() );
                    if( activity.getConfidence() >= 75 ) {
                        currentActivity = "Walking";
                    }
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    if (activity.getConfidence() >= 75) {
                        currentActivity = "Unknown";
                    }
                    Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence() );
                    break;
                }
            }
        }
    }
}

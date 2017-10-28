package com.example.johnmunyi.getupandwalk;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

/**
 * Created by johnmunyi on 10/28/17.
 */

public class ActivityRecognizedService extends IntentService {
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
            handleDetectedActivities( result.getProbableActivities() );
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {

    }
}

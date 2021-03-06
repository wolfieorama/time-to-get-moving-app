package com.example.johnmunyi.getupandwalk;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Button setTime, trackingOn, trackingOff, vibrateOn, vibrateOff;
    private EditText timeToTrack;
    public GoogleApiClient mApiClient;
    private String timeSet;
    private Tracking runner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mApiClient.connect();

        setTime = (Button) findViewById(R.id.setTime);
        trackingOn = (Button) findViewById(R.id.trackOn);
        trackingOff = (Button) findViewById(R.id.trackingOff);
        vibrateOn = (Button) findViewById(R.id.vibrateOn);
        vibrateOff = (Button) findViewById(R.id.vibrateOff);
        timeToTrack = (EditText) findViewById(R.id.timeToTrack);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSet = timeToTrack.getText().toString();
                runner = new Tracking(getApplicationContext());
                runner.execute(timeSet);
            }
        });

        vibrateOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.cancel();
            }
        });

        trackingOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runner.cancel(true);
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent intent = new Intent( this, ActivityRecognizedService.class );
        PendingIntent pendingIntent = PendingIntent.getService( this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates( mApiClient, 3000, pendingIntent );
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

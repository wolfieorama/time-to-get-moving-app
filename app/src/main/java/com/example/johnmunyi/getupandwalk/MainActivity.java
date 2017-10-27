package com.example.johnmunyi.getupandwalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button setTime, trackingOn, trackingOff, vibrateOn, vibrateOff;
    private EditText timeToTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTime = (Button) findViewById(R.id.setTime);
        trackingOn = (Button) findViewById(R.id.trackOn);
        trackingOff = (Button) findViewById(R.id.trackingOff);
        vibrateOn = (Button) findViewById(R.id.vibrateOn);
        vibrateOff = (Button) findViewById(R.id.vibrateOff);
        timeToTrack = (EditText) findViewById(R.id.timeToTrack);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeSet = timeToTrack.getText().toString();

            }
        });
    }
}

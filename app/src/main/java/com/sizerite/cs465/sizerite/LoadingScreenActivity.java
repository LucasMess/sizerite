package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        setTitle("Generating your Sizerite size...");

        // Start a timer and when it reaches a set time, go to next activity.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), SizeRiteFitActivity.class);
                startActivity(intent);
            }
        }, 2000);

    }

}

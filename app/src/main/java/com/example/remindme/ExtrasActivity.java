package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExtrasActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
        Button worldClock = (Button) findViewById(R.id.button_world_clock);
        worldClock.setOnClickListener(this);
        Button stopwatch = (Button) findViewById(R.id.button_stopwatch);
        stopwatch.setOnClickListener(this);
        Button home = (Button) findViewById(R.id.button_home);
        home.setOnClickListener(this);
        //Sets up OnClickListener to the buttons associated with each id
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_world_clock:
                Intent worldClockIntent = new Intent(this, WorldClockActivity.class);
                startActivity(worldClockIntent);
                break;

            case R.id.button_stopwatch:
                Intent stopwatchIntent = new Intent(this, StopwatchActivity.class);
                startActivity(stopwatchIntent);
                break;

            case R.id.button_home:
                Intent homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
                break;

            default:
                break;
            //When any of the buttons are clicked, the associated intent starts and directs the app to the specified activity
        }
    }

}

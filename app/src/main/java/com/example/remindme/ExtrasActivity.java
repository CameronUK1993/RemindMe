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
        /*Directs the call to the created onClick method*/
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
            /*this method allows for all the onClicks to be handled in one place rather than creating a separate method for each button click*/
        }
    }

}

package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button support = (Button) findViewById(R.id.button_support);
        support.setOnClickListener(this);
        Button settings = (Button) findViewById(R.id.button_settings);
        settings.setOnClickListener(this);
        Button extras = (Button) findViewById(R.id.button_extras);
        extras.setOnClickListener(this);
        Button reminders = (Button) findViewById(R.id.button_reminders);
        reminders.setOnClickListener(this);
        /*Directs the call to the created onClick method*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_support:
                Intent supportIntent = new Intent(this, SupportActivity.class);
                startActivity(supportIntent);
                break;

            case R.id.button_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;

            case R.id.button_extras:
                Intent extrasIntent = new Intent(this, ExtrasActivity.class);
                startActivity(extrasIntent);
                break;

            case R.id.button_reminders:
                Intent remindersIntent = new Intent(this, RemindersActivity.class);
                startActivity(remindersIntent);
                break;

            default:
                break;
                /*this method allows for all the onClicks to be handled in one place rather than creating a separate method for each button click*/
        }
    }

}

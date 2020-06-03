package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int Intro_Screen = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent introIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(introIntent);
                finish();
            }
        },Intro_Screen); /*causes 5 second delay before moving onto the home activity*/
    }
}

package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SupportActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        button = (Button) findViewById(R.id.button_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });
        // Setting up an onClickListener for the Home button
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        // When the Home button is clicked, the intent starts and the HomeActivity class is opened
    }
}

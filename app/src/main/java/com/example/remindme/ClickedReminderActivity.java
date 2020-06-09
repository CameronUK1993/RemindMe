package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class ClickedReminderActivity extends AppCompatActivity {

    EditText reminderTitle;
    EditText reminderNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_reminder);

        String title = getIntent().getStringExtra("ARG_TITLE");
        String notes = getIntent().getStringExtra("ARG_NOTES");

        Log.i("Logging -- Func_s", String.format("title %s", title));
        Log.i("Logging -- Func_s", String.format("notes %s", notes));

        reminderTitle = (EditText) findViewById(R.id.ClickedReminderTitle);
        reminderNotes = (EditText) findViewById(R.id.ClickedReminderNotes);
        reminderTitle.setText(title);
        reminderNotes.setText(notes);
    }
}

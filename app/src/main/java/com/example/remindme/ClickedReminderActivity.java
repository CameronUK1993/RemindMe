package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.remindme.models.Reminder;
import com.example.remindme.utility.RemindersRepository;

import java.util.List;

public class ClickedReminderActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ClickedReminderActivity";
    public static final String ARG_REMINDER_ID = "ARG_REMINDER_ID";

    EditText reminderTitle;
    EditText reminderNotes;
    EditText reminderDate;
    EditText reminderTime;

    private boolean mIsNewReminder;

    private final RemindersRepository repository = RemindersRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_reminder);

        reminderTitle = findViewById(R.id.ClickedReminderTitle);
        reminderNotes = findViewById(R.id.ClickedReminderNotes);
        reminderDate = findViewById(R.id.ClickedReminderDate);
        reminderTime = findViewById(R.id.ClickedReminderTime);

        Button back = (Button) findViewById(R.id.button_back);
        back.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString(ARG_REMINDER_ID);
        Log.d(TAG, "onCreate: id "+ id);
        Reminder reminder = repository.getReminder(id);

            Log.d(TAG, "onCreate: " + id);
            reminderTitle.setText(reminder.getTitle());
            reminderNotes.setText(reminder.getNotes());
            reminderDate.setText(reminder.getDate());
            reminderTime.setText(reminder.getTime());

    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_back:
                Intent backIntent = new Intent(this, RemindersActivity.class);
                startActivity(backIntent);
                break;

            default:
                break;
        }
    }

}

package com.example.remindme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.remindme.models.Reminder;
import com.example.remindme.utility.DatabaseHelper;

import java.util.Calendar;

public class AddReminderActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "AddReminderActivity";
    Button datePicker, timePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Spinner spinner;
    private static final String[] list = {"Never", "Daily", "Weekly", "Monthly"};
    String title, note, date, time, repeat;
    EditText titleInput;
    EditText noteInput;
    EditText dateInput;
    EditText timeInput;
    EditText repeatInput;
    DatabaseHelper mDatabaseHelper;

    Calendar mCurrentTime;
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private BroadcastReceiver alarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        datePicker = (Button) findViewById(R.id.pickDate);
        timePicker = (Button) findViewById(R.id.pickTime);
        dateInput = (EditText) findViewById(R.id.theDate);
        timeInput = (EditText) findViewById(R.id.theTime);
        titleInput = (EditText) findViewById(R.id.reminderTitle);
        noteInput = (EditText) findViewById(R.id.reminderNote);
        spinner = (Spinner) findViewById(R.id.repeating);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, list);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        datePicker.setOnClickListener(this);
        timePicker.setOnClickListener(this);

        Button cancel = (Button) findViewById(R.id.button_cancel);
        cancel.setOnClickListener(this);
        Button create = (Button) findViewById(R.id.button_create);
        create.setOnClickListener(this);

        Reminder reminder = new Reminder("some title", "some notes", "some timestamp");

        mDatabaseHelper = new DatabaseHelper(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        String newEntry = titleInput.getText().toString();
        if (titleInput.length() !=0) {
            AddData(newEntry);
        } else {
            toastMessage("You must fill in the text fields!");
        }

        mCurrentTime = Calendar.getInstance();
        mYear = mCurrentTime.get(Calendar.YEAR);
        mMonth = mCurrentTime.get(Calendar.MONTH);
        mDay = mCurrentTime.get(Calendar.DAY_OF_MONTH);
        mHour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        mMinute = mCurrentTime.get(Calendar.MINUTE);


        String title = titleInput.getText().toString();
        String dateFromInput = dateInput.getText().toString();
        String timeFromInput = timeInput.getText().toString();
        String repeatFromInput = spinner.getSelectedItem().toString();

        switch (v.getId()) {

            case R.id.pickDate:
                if (v == datePicker) {

                    final Calendar cal = Calendar.getInstance();
                    mYear = cal.get(Calendar.YEAR);
                    mMonth = cal.get(Calendar.MONTH);
                    mDay = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog;
                    datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            dateInput.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            mCurrentTime.set(Calendar.YEAR, year);
                            mCurrentTime.set(Calendar.MONTH, month);
                            mCurrentTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
                break;



            case R.id.pickTime:
                if (v == timePicker) {

                    final Calendar cal = Calendar.getInstance();
                    mHour = cal.get(Calendar.HOUR_OF_DAY);
                    mMinute = cal.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog;
                    timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            timeInput.setText(hourOfDay + ":" + minute);
                            mCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            mCurrentTime.set(Calendar.MINUTE, minute);
                            mCurrentTime.add(Calendar.SECOND, 0);
                        }
                    }, mHour, mMinute, true);
                    timePickerDialog.show();
                }
                break;

            case R.id.button_create:
                Intent intent = new Intent( this, RemindersActivity.class);
                intent.putExtra("ARG_TITLE", title);
                intent.putExtra("ARG_DATE", dateFromInput);
                intent.putExtra("ARG_TIME", timeFromInput);
                intent.putExtra("ARG REPEAT", repeatFromInput);

                String alarmString = String.valueOf(timeInput.getText());
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent alarmIntent = new Intent(this, AlarmReceiver.class);
                alarmIntent.putExtra(AlarmReceiver.REMINDER_TEXT, title);
                PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);



                startActivity(intent);
                startActivity(alarmIntent);
                break;

            case R.id.button_cancel:
                Intent newIntent = new Intent(this, RemindersActivity.class);
                startActivity(newIntent);
                break;

            default:
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data successfully inserted");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}

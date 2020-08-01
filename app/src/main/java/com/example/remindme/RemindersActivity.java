package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.remindme.adapters.RemindersRecyclerAdapter;
import com.example.remindme.models.Reminder;
import com.example.remindme.utility.DatabaseHelper;
import com.example.remindme.utility.RemindersRepository;

import java.util.ArrayList;
import java.util.List;

public class RemindersActivity extends AppCompatActivity implements View.OnClickListener, RemindersRecyclerAdapter.OnReminderListener {

    private RecyclerView mRecyclerView;
    private List<Reminder> mReminders = new ArrayList<>();
    private RemindersRecyclerAdapter mRemindersRecyclerAdapter;

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    private static final String TAG = "RemindersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: start");
        RemindersRepository instance = RemindersRepository.getInstance();
        mReminders = instance.getReminders();

        Log.i("Logging -- Func_s", String.format("reminder %s", mReminders));
        //Testing the values have been gathered as intended

        setContentView(R.layout.activity_reminders);
        Button home = (Button) findViewById(R.id.button_home);
        home.setOnClickListener(this);
        Button newReminder = (Button) findViewById(R.id.button_new);
        newReminder.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.recyclerView);
        Log.d(TAG, "onCreate: setting recycler view");
        initRecyclerView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_home:
                Intent homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
                break;

            case R.id.button_new:
                Intent newIntent = new Intent(this, AddReminderActivity.class);
                startActivity(newIntent);
                break;

            default:
                break;
            //When one of the buttons is clicked, the associated intent begins and directs the app to the correct activity
        }
    }


    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        Log.d(TAG, "initRecyclerView: " + mReminders.size());
        mRemindersRecyclerAdapter = new RemindersRecyclerAdapter(mReminders, this);
        mRecyclerView.setAdapter(mRemindersRecyclerAdapter);
        //Creating the recycler view ready for data to be added
    }

    @Override
    public void onReminderClick(Reminder reminder) {
        Log.d(TAG, "onReminderClick: clicked" + reminder);

        Intent clickedIntent = new Intent(this, ClickedReminderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("ARG_REMINDER_ID", reminder.getId());
        clickedIntent.putExtras(bundle);
        startActivity(clickedIntent);
        finish();
    }
}

package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

import java.util.ArrayList;

public class RemindersActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ArrayList<Reminder> mReminder = new ArrayList<>();
    private RemindersRecyclerAdapter mRemindersRecyclerAdapter;

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We must extract those values from the payload
        String title = getIntent().getStringExtra("ARG_TITLE");
        String notes = getIntent().getStringExtra("ARG_NOTES");
        String date = getIntent().getStringExtra("ARG_DATE");
        String time = getIntent().getStringExtra("ARG_TIME");

        Log.i("Logging -- Func_s", String.format("title %s", title));
        Log.i("Logging -- Func_s", String.format("notes %s", notes));
        Log.i("Logging -- Func_s", String.format("date %s", date));
        Log.i("Logging -- Func_s", String.format("time %s", time));
        
        setContentView(R.layout.activity_reminders);
        Button home = (Button) findViewById(R.id.button_home);
        home.setOnClickListener(this);
        Button newReminder = (Button) findViewById(R.id.button_new);
        newReminder.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.recyclerView);

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
        }
    }


    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRemindersRecyclerAdapter = new RemindersRecyclerAdapter(mReminder);
        mRecyclerView.setAdapter(mRemindersRecyclerAdapter);
    }
}

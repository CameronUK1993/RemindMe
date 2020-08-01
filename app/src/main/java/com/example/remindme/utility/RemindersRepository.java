package com.example.remindme.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.remindme.models.Reminder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RemindersRepository {
    private static final String TAG = "RemindersRepository";
    public static final String ARG_TITLE = "ARG_TITLE";
    public static final String ARG_NOTES = "ARG_NOTES";
    public static final String ARG_DATE = "ARG_DATE";
    public static final String ARG_TIME = "ARG_TIME";
    private static final String ARG_REMINDERS = "ARG_REMINDERS";
    private final Gson gson = new Gson();
    private List<Reminder> reminders = null;

    private static RemindersRepository INSTANCE = null;
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    private RemindersRepository(){

    }

    public void setContext(Context context){

        this.context = context;
    }

    public static RemindersRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RemindersRepository();
        }

        return INSTANCE;
    }

    public void saveReminder(Reminder reminder){
        Log.d(TAG, "saveReminder: ");
        if (reminders == null){
            reminders = new ArrayList<Reminder>();
        }
        reminders.add(reminder);
        sharedPreferences = context.getSharedPreferences("com.example.remindme.reminders", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String json = gson.toJson(reminders);
        Log.d(TAG, "saveReminder: json: " + json);
        editor.putString(ARG_REMINDERS, json);
        editor.putString(ARG_TITLE, reminder.getTitle());
        editor.putString(ARG_NOTES, reminder.getNotes());
        editor.putString(ARG_DATE, reminder.getDate());
        editor.putString(ARG_TIME, reminder.getTime());

        editor.commit();
    }

    public List<Reminder> getReminders(){
        sharedPreferences = context.getSharedPreferences("com.example.remindme.reminders", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(ARG_REMINDERS, null);
        if (json == null){
            return new ArrayList<>();
        }

        Type listType = new TypeToken<List<Reminder>>(){}.getType();
        reminders = gson.fromJson(json, listType);

        return reminders;
    }

    public Reminder getReminder(String id) {
        if (reminders == null){
            sharedPreferences = context.getSharedPreferences("com.example.remindme.reminders", Context.MODE_PRIVATE);
            String json = sharedPreferences.getString(ARG_REMINDERS, null);
            if (json == null){
                reminders = new ArrayList<>();
            }
        }
        for (Reminder reminder : reminders) {
            if (reminder.getId().equals(id)){
                return reminder;
            }
        }
        return null;
    }
}

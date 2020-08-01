package com.example.remindme;

import android.app.Application;
import android.util.Log;

import com.example.remindme.utility.RemindersRepository;

public class RemindMeApplication extends Application {

    private static final String TAG = "RemindMeApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        RemindersRepository instance = RemindersRepository.getInstance();
        instance.setContext(this);
        Log.d(TAG, "onCreate: ");
    }
}

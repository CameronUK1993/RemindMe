package com.example.remindme.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Reminder implements Parcelable {

    private boolean getId;
    private String id;
    private String title;
    private String notes;
    private String date;
    private String time;

    public Reminder(String some_title, String some_notes, String some_date, String some_time) {
        this.id = UUID.randomUUID().toString();
        this.title = some_title;
        this.notes = some_notes;
        this.date = some_date;
        this.time = some_time;
    }

    protected Reminder(Parcel in) {
        title = in.readString();
        notes = in.readString();
        date = in.readString();
        time = in.readString();
    }

    public static final Creator<Reminder> CREATOR = new Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {return id;}


    @Override
    public String toString() {
        return "Reminder{" +
                "title='" + title + '\'' +
                ", notes='" + notes + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(notes);
        dest.writeString(date);
        dest.writeString(time);
    }
}

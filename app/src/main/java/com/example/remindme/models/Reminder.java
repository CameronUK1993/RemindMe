package com.example.remindme.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Reminder implements Parcelable {

    private String title;
    private String date;
    private String time;
    private String repeat;

    public Reminder(String title, String date, String time, String repeat) {
        this.title = title;
        this.time = time;
        this.date = date;
        this.repeat = repeat;
    }

    public Reminder(String some_title, String some_notes, String some_timestamp) {

    }

    protected Reminder(Parcel in) {
        title = in.readString();
        date = in.readString();
        time = in.readString();
        repeat = in.readString();
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

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }


    @Override
    public String toString() {
        return "Reminder{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", repeat='" + repeat + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(repeat);
    }
}

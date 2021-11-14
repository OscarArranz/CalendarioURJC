package com.example.calendariourjc.model;

import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEvent {

    Date date;
    String title;
    String description;

    public DateEvent(String date, String title, String description) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.title = title;
        this.description = description;
    }

    public void save(SharedPreferences sharedPrefs) {
        int lastIndex = sharedPrefs.getInt("lastIndex", 0);
        sharedPrefs.edit().putInt("lastIndex", lastIndex + 1)
                .putString(lastIndex + "-DateEvent-Date", date.toString())
                .putString(lastIndex + "-DateEvent-Title", title)
                .putString(lastIndex + "-DateEvent-Description", description)
                .commit();
    }

    public void delete() {

    }

    public void modify(String date, String title, String description) {

    }

}

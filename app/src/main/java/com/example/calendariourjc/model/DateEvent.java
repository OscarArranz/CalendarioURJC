package com.example.calendariourjc.model;

import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateEvent {

    Date date;
    String title;
    String description;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public DateEvent(int id, String date, String title, String description) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.title = title;
        this.description = description;
        this.id = id;
    }

    public void save(SharedPreferences sharedPrefs) {
        int lastIndex = sharedPrefs.getInt("lastIndex", 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        sharedPrefs.edit().putInt("lastIndex", lastIndex + 1)
                .putString(lastIndex + "-DateEvent-Date", dateFormat.format(date))
                .putString(lastIndex + "-DateEvent-Title", title)
                .putString(lastIndex + "-DateEvent-Description", description)
                .commit();
    }

    public static List<DateEvent> getAll(SharedPreferences sharedPrefs) {
        ArrayList<DateEvent> dateEvents = new ArrayList<>();
        int lastIndex = sharedPrefs.getInt("lastIndex", 0);

        for(int i = 0; i < lastIndex; i++) {
            String date = sharedPrefs.getString(i + "-DateEvent-Date", "");

            if(date == "") continue;

            String title = sharedPrefs.getString(i + "-DateEvent-Title",  "");
            String description = sharedPrefs.getString(i + "-DateEvent-Description", "");

            dateEvents.add(new DateEvent(i, date, title, description));

            System.out.print(date);
        }

        return dateEvents;
    }

    public void delete(SharedPreferences sharedPrefs) {
        sharedPrefs.edit().remove(id + "-DateEvent-Date")
                .remove(id + "-DateEvent-Title")
                .remove(id + "-DateEvent-Description")
                .commit();
    }

    public void modify(String date, String title, String description) {

    }

}

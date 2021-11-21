package com.example.calendariourjc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calendariourjc.model.DateEvent;

public class AddDateEventActivity extends AppCompatActivity {

    EditText date;
    EditText title;
    EditText description;

    Button add_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_date_event);

        date = findViewById(R.id.date);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        add_btn = findViewById(R.id.add_btn);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        add_btn.setOnClickListener(view -> {
            String newDate = date.getText().toString();
            String newTitle = title.getText().toString();
            String newDescription = description.getText().toString();

            DateEvent dateEvent = new DateEvent(newDate, newTitle, newDescription);

            dateEvent.save(sharedPrefs);

            Intent events = new Intent(AddDateEventActivity.this, EventsActivity.class);
            AddDateEventActivity.this.startActivity(events);
        });

    }
}

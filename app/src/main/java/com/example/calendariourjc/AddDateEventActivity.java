package com.example.calendariourjc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calendariourjc.model.DateEvent;

public class AddDateEventActivity extends AppCompatActivity {

    EditText date;
    EditText title;
    EditText description;

    Button add_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_add_date_event);

        SharedPreferences sharedPrefs = this.getPreferences(Context.MODE_PRIVATE);

        add_btn.setOnClickListener(view -> {
            String newDate = date.getText().toString();
            String newTitle = title.getText().toString();
            String newDescription = title.getText().toString();

            DateEvent dateEvent = new DateEvent(newDate, newTitle, newDescription);

            dateEvent.save(sharedPrefs);
        });

    }
}

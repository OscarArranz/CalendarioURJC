package com.example.calendariourjc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calendariourjc.model.DateEvent;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

    Button go_add_event_btn;
    LinearLayout eventsView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_events);
        go_add_event_btn = findViewById(R.id.go_add_event_btn);
        eventsView = findViewById(R.id.events_view);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        ArrayList<DateEvent> dateEvents = new ArrayList<>(DateEvent.getAll(sharedPrefs));

        for (DateEvent dateEvent: dateEvents) {
            TextView date = new TextView(this);
            TextView title = new TextView(this);
            TextView description = new TextView(this);

            Button delete = new Button(this);
            Button viewBtn = new Button(this);
            delete.setText("Borrar");
            viewBtn.setText("Ver");

            LinearLayout buttons = new LinearLayout(this);
            buttons.setOrientation(LinearLayout.HORIZONTAL);
            buttons.addView(viewBtn);
            buttons.addView(delete);

            delete.setOnClickListener(view -> {
                dateEvent.delete(sharedPrefs);
                eventsView.removeView(title);
                eventsView.removeView(date);
                eventsView.removeView(description);
                eventsView.removeView(buttons);
            });

            viewBtn.setOnClickListener(view -> {
                Intent eventDetail = new Intent(EventsActivity.this, EventDetailActivity.class);
                eventDetail
                        .putExtra("id", dateEvent.getId())
                        .putExtra("title", dateEvent.getTitle())
                        .putExtra("date", dateEvent.getDate())
                        .putExtra("description", dateEvent.getDescription());
                EventsActivity.this.startActivity(eventDetail);
            });

            date.setText("Fecha: " + dateEvent.getDate());
            title.setText("Titulo: " + dateEvent.getTitle());
            description.setText("Descripcion: " + dateEvent.getDescription());

            eventsView.addView(title);
            eventsView.addView(date);
            eventsView.addView(description);
            eventsView.addView(buttons);

            LinearLayout separator = new LinearLayout(this);
            eventsView.addView(separator, 10, 60);
        }

        go_add_event_btn.setOnClickListener(view -> {
            Intent addDate = new Intent(EventsActivity.this, AddDateEventActivity.class);
            EventsActivity.this.startActivity(addDate);
        });
    }

}

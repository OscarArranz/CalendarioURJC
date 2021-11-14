package com.example.calendariourjc;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calendariourjc.ui.dashboard.DashboardFragment;

public class EventsActivity extends AppCompatActivity {

    Button go_add_event_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_events);

        go_add_event_btn.setOnClickListener(view -> {
            Intent addDate = new Intent(EventsActivity.this, AddDateEventActivity.class);
            EventsActivity.this.startActivity(addDate);
        });
    }
}

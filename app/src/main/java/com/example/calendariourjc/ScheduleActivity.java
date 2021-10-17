package com.example.calendariourjc;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calendariourjc.ui.dashboard.DashboardFragment;

public class ScheduleActivity extends AppCompatActivity {

    private WebView scheduleWeb;
    private Button backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        scheduleWeb = findViewById(R.id.scheduleWeb);
        backBtn = findViewById(R.id.back_btn);

        scheduleWeb.loadUrl("https://gestion2.urjc.es/horarios/");
        backBtn.setOnClickListener(view -> {
            Intent dashboard = new Intent(ScheduleActivity.this, DashboardFragment.class);
            ScheduleActivity.this.startActivity(dashboard);
        });
    }

}

package com.example.calendariourjc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.calendariourjc.model.DateEvent;

public class CommentActivity extends AppCompatActivity {

    EditText comment;
    Button addCommentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String description = intent.getStringExtra("description");

        comment = findViewById(R.id.comment);
        addCommentBtn = findViewById(R.id.add_comment_btn);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        addCommentBtn.setOnClickListener(view -> {
            DateEvent.addComment(comment.getText().toString(), id, sharedPrefs);

            Intent goBack = new Intent(CommentActivity.this, EventDetailActivity.class);
            goBack.putExtra("id", id)
                .putExtra("title", title)
                .putExtra("date", date)
                .putExtra("description", description);
            CommentActivity.this.startActivity(goBack);
        });
    }
}
package com.example.calendariourjc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calendariourjc.model.DateEvent;

import java.util.HashMap;
import java.util.Map;

public class EventDetailActivity extends AppCompatActivity {

    Button addCommentBtn;
    TextView titleView;
    TextView dateView;
    TextView descriptionView;
    LinearLayout commentsview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        titleView = findViewById(R.id.event_title);
        dateView = findViewById(R.id.event_date);
        descriptionView = findViewById(R.id.event_description);
        commentsview = findViewById(R.id.comments_view);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String description = intent.getStringExtra("description");

        titleView.setText(title);
        dateView.setText("Fecha: " + date);
        descriptionView.setText("Descripción:\n\n" + description);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        HashMap<Integer, String> comments = new HashMap<>(DateEvent.getAllComments(id, sharedPrefs));

        for (Map.Entry<Integer, String> comment: comments.entrySet()) {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView commentText = new TextView(this);
            commentText.setText(comment.getValue());

            Button deleteBtn = new Button(this);
            deleteBtn.setText("Borrar comentario");

            deleteBtn.setOnClickListener(view -> {
                commentsview.removeView(layout);
                DateEvent.deleteComment(id, comment.getKey(), sharedPrefs);
            });

            layout.addView(commentText);
            layout.addView(deleteBtn);

            commentsview.addView(layout);
        }

        addCommentBtn = findViewById(R.id.add_comment_btn);

        addCommentBtn.setOnClickListener(view -> {
            Intent addComment = new Intent(EventDetailActivity.this, CommentActivity.class);
            addComment.putExtra("id", id)
                    .putExtra("title", title)
                    .putExtra("date", date)
                    .putExtra("description", description);
            EventDetailActivity.this.startActivity(addComment);
        });
    }

}
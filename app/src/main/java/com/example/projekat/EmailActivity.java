package com.example.projekat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {

    TextView subject, text, from, date;
    ImageView btnCancel, btnReply, btnReplyAll, btnForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        subject = findViewById(R.id.subject);
        text = findViewById(R.id.text);
        from = findViewById(R.id.from);
        date = findViewById(R.id.date);

        btnCancel = findViewById(R.id.btnCancel);
        btnReply = findViewById(R.id.btnReply);
        btnReplyAll = findViewById(R.id.btnReplyAll);
        btnForward = findViewById(R.id.btnForward);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnReplyAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Intent intent = getIntent();
        int index = intent.getIntExtra("ITEM_INDEX", -1);

        if (index >= 0) {
            subject.setText(MyApplication.messages.get(index).getSubject());
            text.setText(MyApplication.messages.get(index).getContent());
            from.setText(MyApplication.messages.get(index).getFrom());
            date.setText(MyApplication.messages.get(index).getDateTime().toString());
        }
    }
}

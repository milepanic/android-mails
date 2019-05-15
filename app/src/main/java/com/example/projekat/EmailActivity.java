package com.example.projekat;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.projekat.Models.Message;

public class EmailActivity extends AppCompatActivity {

    Message message;
    int index;
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

        Intent intent = getIntent();
        index = intent.getIntExtra("ITEM_INDEX", -1);

        message = MyApplication.messages.get(index);

        if (index >= 0) {
            subject.setText(message.getSubject());
            text.setText(message.getContent());
            from.setText(message.getFrom());
            date.setText(message.getDateTime().toString());
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(EmailActivity.this);
                dialog.setMessage("Are you sure you want to delete this email?");

                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Backendless.Persistence.of(Message.class).remove(message, new AsyncCallback<Long>() {
                            @Override
                            public void handleResponse(Long response) {
                                MyApplication.messages.remove(index);

                                Toast.makeText(
                                        EmailActivity.this,
                                        "Message deleted",
                                        Toast.LENGTH_SHORT).show();

                                finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(
                                        EmailActivity.this,
                                        "Error: " + fault.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialog.show();
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


    }
}

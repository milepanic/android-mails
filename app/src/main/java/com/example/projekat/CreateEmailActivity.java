package com.example.projekat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.projekat.Models.Message;

import java.util.Date;

public class CreateEmailActivity extends AppCompatActivity {

    EditText etSubject, etTo, etMessage, etCC, etBCC;
    ImageView btnSend, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etSubject = findViewById(R.id.etSubject);
        etTo = findViewById(R.id.etTo);
        etMessage = findViewById(R.id.etMessage);
        etCC = findViewById(R.id.etCC);
        etBCC = findViewById(R.id.etBCC);

        btnSend = findViewById(R.id.btnReply);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailsIntent = new Intent(CreateEmailActivity.this, EmailsActivity.class);

                startActivity(emailsIntent);
                finish();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = etSubject.getText().toString();
                String to = etTo.getText().toString();
                String text = etMessage.getText().toString();
                String CC = etCC.getText().toString();
                String BCC = etBCC.getText().toString();

                if (subject.isEmpty() || to.isEmpty() || text.isEmpty()) {
                    Toast.makeText(CreateEmailActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Message message = new Message();
                message.setBcc(BCC);
                message.setCc(CC);
                message.setSubject(subject);
                message.setContent(text);
                message.setFrom(MyApplication.auth.getEmail());
                message.setTo(to);
                message.setDateTime(new Date());

                Backendless.Persistence.save(message, new AsyncCallback<Message>() {
                    @Override
                    public void handleResponse(Message response) {
                        Toast.makeText(
                                CreateEmailActivity.this,
                                "Message sent successfully!",
                                Toast.LENGTH_SHORT).show();

                        etSubject.setText("");
                        etBCC.setText("");
                        etCC.setText("");
                        etMessage.setText("");
                        etTo.setText("");
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(
                                CreateEmailActivity.this,
                                "Error: " + fault.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

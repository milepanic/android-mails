package com.example.projekat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.projekat.Models.Message;
import com.example.projekat.Tasks.DownloadFileTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

public class EmailActivity extends AppCompatActivity {

    int index;
    Message message;
    TextView subject, text, from, date;
    ImageView imageView;

    String inputText;

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
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        index = intent.getIntExtra("ITEM_INDEX", -1);

        message = MyApplication.messages.get(index);

        if (index >= 0) {
            subject.setText(message.getSubject());
            text.setText(message.getContent());
            from.setText(message.getFrom());
            date.setText(DateFormat.getDateTimeInstance().format(message.getDateTime()));
            if (message.getFilename() != null) {
                getImage(message.getFilename());
            }
        }
    }

    public void replyTo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reply:");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputText = input.getText().toString();

                Message replyMessage = new Message();
                replyMessage.setSubject(message.getSubject());
                replyMessage.setContent(inputText);
                replyMessage.setFrom(MyApplication.auth.getEmail());
                replyMessage.setTo(message.getFrom());
                replyMessage.setDateTime(new Date());

                Backendless.Persistence.save(replyMessage, new AsyncCallback<Message>() {
                    @Override
                    public void handleResponse(Message response) {
                        Toast.makeText(
                                EmailActivity.this,
                                "Message replied successfully!",
                                Toast.LENGTH_SHORT).show();
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
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void replyAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reply to all:");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputText = input.getText().toString();

                Message replyAllMessage = new Message();
                replyAllMessage.setSubject(message.getSubject());
                replyAllMessage.setContent(inputText);
                replyAllMessage.setFrom(MyApplication.auth.getEmail());
                replyAllMessage.setTo(message.getFrom());
                replyAllMessage.setCc(message.getCc());
                replyAllMessage.setDateTime(new Date());

                Backendless.Persistence.save(replyAllMessage, new AsyncCallback<Message>() {
                    @Override
                    public void handleResponse(Message response) {
                        Toast.makeText(
                                EmailActivity.this,
                                "Message replied successfully!",
                                Toast.LENGTH_SHORT).show();
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
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void forwardMail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forward mail to:");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputText = input.getText().toString();

                Message forwardMessage = new Message();
                forwardMessage.setSubject(message.getSubject());
                forwardMessage.setContent(message.getContent());
                forwardMessage.setFrom(MyApplication.auth.getEmail());
                forwardMessage.setTo(inputText);
                forwardMessage.setDateTime(new Date());

                Backendless.Persistence.save(forwardMessage, new AsyncCallback<Message>() {
                    @Override
                    public void handleResponse(Message response) {
                        Toast.makeText(
                                EmailActivity.this,
                                "Message forwarded successfully!",
                                Toast.LENGTH_SHORT).show();
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
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void deleteMail() {
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

    public void getImage(String filename) {
        try {
            URL url = new URL(
                            "https://backendlessappcontent.com/" + MyApplication.APPLICATION_ID + "/" +
                                    MyApplication.API_KEY + "/files/uploadedImages/" + filename
            );

            DownloadFileTask task = new DownloadFileTask(imageView);
            task.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_email, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_reply:
                replyTo();
                break;

            case R.id.action_reply_all:
                replyAll();
                break;

            case R.id.action_forward:
                forwardMail();
                break;

            case R.id.action_delete:
                deleteMail();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}



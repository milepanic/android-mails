package com.example.projekat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.example.projekat.Models.Attachment;
import com.example.projekat.Models.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEmailActivity extends AppCompatActivity {

    EditText etSubject, etTo, etMessage, etCC, etBCC;
    ImageView btnSend, btnCancel, imageAttachment;
    Button btnCamera, btnGallery;
    Bitmap bitmap;

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

        imageAttachment = findViewById(R.id.imageAttachment);
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);

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
                if (imageAttachment.getVisibility() == View.VISIBLE) {
                    String timestamp = new SimpleDateFormat("yyyyMMdd__HHmmss").format(new Date());
                    final String fileName = "PNG_" + timestamp + ".png";

                    final Attachment attachment = new Attachment();
                    attachment.setType("image");
                    attachment.setName(fileName);
                    attachment.setData("uploadedImages/" + fileName);

                    Backendless.Files.Android.upload(
                            bitmap,
                            Bitmap.CompressFormat.PNG,
                            100,
                            fileName,
                            "uploadedImages",
                            new AsyncCallback<BackendlessFile>() {
                                @Override
                                public void handleResponse(BackendlessFile response) {
                                    Backendless.Persistence.save(attachment, new AsyncCallback<Attachment>() {
                                        @Override
                                        public void handleResponse(Attachment response) {
                                            uploadEmail(fileName);
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

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(
                                            CreateEmailActivity.this,
                                            "Error: " + fault.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    uploadEmail("");
                }

            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select image"), 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            bitmap = (Bitmap) data.getExtras().get("data");

            imageAttachment.setImageBitmap(bitmap);
            imageAttachment.setVisibility(View.VISIBLE);
        }

        if (requestCode == 2) {
            Uri imageUri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                imageAttachment.setImageBitmap(bitmap);
                imageAttachment.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadEmail(String filename) {
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
        if (filename != "") {
            message.setFilename(filename);
        }

        Backendless.Persistence.save(message, new AsyncCallback<Message>() {
            @Override
            public void handleResponse(Message response) {
                finish();
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
}

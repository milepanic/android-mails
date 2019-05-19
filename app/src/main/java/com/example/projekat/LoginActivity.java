package com.example.projekat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {
                String userObjectId = UserIdStorageFactory.instance().getStorage().get();

                Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        MyApplication.auth = response;

                        Intent emailsIntent = new Intent(LoginActivity.this, EmailsActivity.class);

                        startActivity(emailsIntent);
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
//                        Toast.makeText(
//                                LoginActivity.this,
//                                "Error: " + fault.getMessage(),
//                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(
                        LoginActivity.this,
                        "Error: " + fault.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString();

                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Unesite sve podatke", Toast.LENGTH_SHORT).show();
                }

                BackendlessUser user = new BackendlessUser();
                user.setEmail(emailText);
                user.setPassword(passwordText);
                user.setProperty("name", "Mile Panic");

                Backendless.UserService.login(emailText, passwordText, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        MyApplication.auth = response;

                        Toast.makeText(
                                LoginActivity.this,
                                "Successfully logged in",
                                Toast.LENGTH_SHORT).show();

                        Intent emailsIntent = new Intent(LoginActivity.this, EmailsActivity.class);

                        startActivity(emailsIntent);
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
//                        Toast.makeText(
//                                LoginActivity.this,
//                                "Error: " + fault.getMessage(),
//                                Toast.LENGTH_SHORT).show();
                    }
                }, true);

//                Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
//                    @Override
//                    public void handleResponse(BackendlessUser response) {
//                        Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void handleFault(BackendlessFault fault) {
//                        Toast.makeText(LoginActivity.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }
}

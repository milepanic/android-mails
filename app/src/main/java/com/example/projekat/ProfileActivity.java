package com.example.projekat;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView initial, username, email;
    EditText editEmail, editUsername, editPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        initial = findViewById(R.id.initial);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        initial.setText(MyApplication.auth.getProperty("username").toString().toUpperCase().charAt(0) + "");
        username.setText(MyApplication.auth.getProperty("username").toString());
        email.setText(MyApplication.auth.getEmail());
    }

    public void openEmails() {
        Intent emailsIntent = new Intent(this, EmailsActivity.class);
        startActivity(emailsIntent);
        finish();
    }

    public void logout() {
        Backendless.UserService.logout(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Toast.makeText(
                        ProfileActivity.this,
                        "Successfully logged out!",
                        Toast.LENGTH_SHORT).show();

                Intent loginIntent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(
                        ProfileActivity.this,
                        "Error: " + fault.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editProfile() {
        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        editEmail.setText(MyApplication.auth.getEmail());
        editUsername.setText(MyApplication.auth.getProperty("username").toString());
        editPassword.setText("");

        editEmail.setVisibility(View.VISIBLE);
        editUsername.setVisibility(View.VISIBLE);
        editPassword.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    public void updateProfile() {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String username = editUsername.getText().toString();

        MyApplication.auth.setEmail(email);
        MyApplication.auth.setPassword(password);
        MyApplication.auth.setProperty("username", username);

        Backendless.UserService.update(MyApplication.auth, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                editEmail.setVisibility(View.GONE);
                editUsername.setVisibility(View.GONE);
                editPassword.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.GONE);

                Toast.makeText(
                        ProfileActivity.this,
                        "Successfully updated profile",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(
                        ProfileActivity.this,
                        "Error: " + fault.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            this.logout();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_emails) {
            this.openEmails();
        } else if (id == R.id.nav_profile) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

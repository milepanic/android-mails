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
import android.widget.AdapterView;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.projekat.Adapters.EmailsAdapter;
import com.example.projekat.Models.Message;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EmailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] HEADERS = {"First header", "Second header", "Third"};

    String responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emails);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateEmail(view);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

//        getData();
//        System.out.println("Resss " + responseData);

        ListView listView = (ListView) findViewById(R.id.listView);

        EmailsAdapter emailsAdapter = new EmailsAdapter(this, getData());
        listView.setAdapter(emailsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent emailIntent = new Intent(getApplicationContext(), EmailActivity.class);
                emailIntent.putExtra("ITEM_INDEX", position);

                startActivity(emailIntent);
            }
        });

        Message message = new Message();
        message.setId(1);
        message.setBcc("bcc");
        message.setCc("cc");
        message.setSubject("NASLOV");
        message.setContent("poruka tijelo body");
        message.setFrom("Korisnik 2");
        message.setTo("Korisnik 5");
        message.setDateTime(new Date());

        Backendless.Data.of(Message.class).save(message, new AsyncCallback<Message>() {
            @Override
            public void handleResponse(Message response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

    }

    private String getData() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
//                .header("Authorization", "token abcd")
                .url("https://api.github.com/users/codepath")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (! response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

            }
        });

        return null;
    }

    public void openCreateEmail(View view) {
        Intent createEmailIntent = new Intent(this, CreateEmailActivity.class);

        startActivity(createEmailIntent);
    }

    public void openProfile() {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_emails) {

        } else if (id == R.id.nav_profile) {
            this.openProfile();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}



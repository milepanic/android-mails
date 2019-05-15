package com.example.projekat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.projekat.Adapters.EmailsAdapter;
import com.example.projekat.Models.Message;

import java.util.List;

public class EmailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;

    ListView listView;
    EmailsAdapter emailsAdapter;

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

        prefs = getSharedPreferences(MyApplication.PREFERENCES, MODE_PRIVATE);

        listView = findViewById(R.id.listView);

        displayEmails();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent emailIntent = new Intent(getApplicationContext(), EmailActivity.class);
                emailIntent.putExtra("ITEM_INDEX", position);

                startActivityForResult(emailIntent, 1);
            }
        });

        autoRefreshEmails();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            emailsAdapter.notifyDataSetChanged();
        } else if (requestCode == 2) {
            emailsAdapter.notifyDataSetChanged();
            displayEmails();
            autoRefreshEmails();
        }
    }

    public void displayEmails() {
        String mail = MyApplication.auth.getEmail();
        String whereClause = "to = '" + mail + "' OR cc = '" + mail + "' OR bcc = '" + mail + "'";

        String sortByClause = "dateTime";

        boolean orderByDesc = prefs.getBoolean("orderByDesc", false);
        if (orderByDesc) {
            sortByClause += " DESC";
        }

        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        query.setSortBy(sortByClause);

        Backendless.Persistence.of(Message.class).find(query, new AsyncCallback<List<Message>>() {
            @Override
            public void handleResponse(List<Message> response) {
                MyApplication.messages = response;

                emailsAdapter = new EmailsAdapter(EmailsActivity.this, MyApplication.messages);
                listView.setAdapter(emailsAdapter);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(
                        EmailsActivity.this,
                        "Error: " + fault.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void autoRefreshEmails() {
        final int refreshRate = prefs.getInt("refreshRate", 10000);
        final Handler handler = new Handler();

        Runnable refresh = new Runnable() {
            @Override
            public void run() {
                displayEmails();

                handler.postDelayed(this, refreshRate);
            }
        };

        handler.postDelayed(refresh, refreshRate);
    }

    public void openCreateEmail(View view) {
        Intent createEmailIntent = new Intent(this, CreateEmailActivity.class);

        startActivity(createEmailIntent);
    }

    public void openProfile() {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
        finish();
    }

    public void openSettings() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);

        startActivityForResult(settingsIntent, 2);
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
            this.openSettings();
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



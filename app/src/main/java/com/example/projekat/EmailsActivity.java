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

public class EmailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    String[] HEADERS = {"First header", "Second header", "Third"};
//    String[] TEXTS = {"First mail", "Second mail hello world", "third one"};
//    String[] DATES = {"3 hours ago", "5 days ago", "6 days ago"};
//    String[] SENDERS = {"Bob Ross", "John Doe", "Smith"};

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

//        ListView listView = findViewById(R.id.listView);

//        CustomAdapter customAdapter = new CustomAdapter();
//        listView.setAdapter(customAdapter);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

//    class CustomAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return HEADERS.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            convertView = getLayoutInflater().inflate(R.layout.view_emails, null);
//
//            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
//            TextView header = convertView.findViewById(R.id.header);
//            TextView message = convertView.findViewById(R.id.message);
//            TextView date = convertView.findViewById(R.id.date);
//
//            imageView.setImageResource(SENDERS[position]);
//            header.setText(HEADERS[position]);
//            message.setText(TEXTS[position]);
//            date.setText(DATES[position]);
//            header.setText(HEADERS[i]);
//
//            return convertView;
//        }
//    }
}



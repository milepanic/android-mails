package com.example.projekat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        int index = intent.getIntExtra("ITEM_INDEX", -1);

        if (index >= 0) {
            // pronaci email po id-u

            // poslati podatke u layout
            TextView header = (TextView) findViewById(R.id.header);
            TextView text = (TextView) findViewById(R.id.text);
            TextView sender = (TextView) findViewById(R.id.sender);
            TextView date = (TextView) findViewById(R.id.date);

            header.setText("Naslov");
            text.setText("Text poruke");
            sender.setText("korisnik@gmail.com");
            date.setText("21.05.2019.");
        }
    }
}

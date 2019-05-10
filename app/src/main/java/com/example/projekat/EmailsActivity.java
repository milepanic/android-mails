package com.example.projekat;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EmailsActivity extends AppCompatActivity {

    String[] HEADERS = {"First header", "Second header", "Third"};
    String[] TEXTS = {"First mail", "Second mail hello world", "third one"};
    String[] DATES = {"3 hours ago", "5 days ago", "6 days ago"};
    String[] SENDERS = {"Bob Ross", "John Doe", "Smith"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emails);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.listView);

//        CustomAdapter customAdapter = new CustomAdapter();
//        listView.setAdapter(customAdapter);
    }

    public void createEmail(View view) {
        Intent createEmailIntent = new Intent(this, CreateEmailActivity.class);

        startActivity(createEmailIntent);
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



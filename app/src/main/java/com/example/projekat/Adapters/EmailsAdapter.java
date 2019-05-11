package com.example.projekat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projekat.R;

public class EmailsAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    int[] ID = {1, 2, 3};
    String[] HEADERS = {"First header", "Second header", "Third"};
    String[] TEXTS = {"First mail", "Second mail hello world", "third one"};
    String[] DATES = {"3 hours ago", "5 days ago", "6 days ago"};
    String[] SENDERS = {"Bob Ross", "John Doe", "Smith"};

    public EmailsAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ID.length;
    }

    @Override
    public Object getItem(int position) {
        return ID[position];
    }

    @Override
    public long getItemId(int position) {
        return ID[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.list_emails, null);

//        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        TextView header = (TextView) v.findViewById(R.id.header);
        TextView message = (TextView) v.findViewById(R.id.message);
        TextView date = (TextView) v.findViewById(R.id.date);

//        imageView.setImageResource(SENDERS[position]);
        header.setText(HEADERS[position]);
        message.setText(TEXTS[position]);
        date.setText(DATES[position]);

        return v;
    }
}

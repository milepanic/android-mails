package com.example.projekat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projekat.Models.Message;
import com.example.projekat.R;

import java.text.DateFormat;
import java.util.List;

public class EmailsAdapter extends BaseAdapter {

    private List<Message> messages;
    private Context context;

    public EmailsAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        View v = mInflater.inflate(R.layout.list_emails, null);
        convertView = inflater.inflate(R.layout.list_emails, null);

//        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        TextView header = (TextView) convertView.findViewById(R.id.subject);
        TextView message = (TextView) convertView.findViewById(R.id.message);
        TextView date = (TextView) convertView.findViewById(R.id.date);

//        imageView.setImageResource(SENDERS[position]);
        header.setText(messages.get(position).getSubject());
        message.setText(messages.get(position).getContent());
        date.setText(DateFormat.getDateTimeInstance().format(messages.get(position).getDateTime()));

        return convertView;
    }
}

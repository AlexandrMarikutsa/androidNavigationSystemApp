package com.demo.navigationsysapp.app.activities.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.demo.navigationsysapp.app.R;
import com.demo.navigationsysapp.app.activities.pojo.Event;
import com.demo.navigationsysapp.app.activities.service.DownloadImageTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class EventAdapter extends BaseAdapter {
    private List<Event> events;
    private LayoutInflater layoutInflater;
    private Context context;

    public EventAdapter(Context context, List<Event> events) {
        this.events = events;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.mylist,parent,false);
        }
        Event event = getEvent(position);
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(event.getEventName());
        TextView textView1 = (TextView) view.findViewById(R.id.createdAt);
        textView1.setText(event.getType() + ' ' + event.getCreatedAt());
        ImageView imageView = (ImageView) view.findViewById(R.id.avatar);
        Picasso.with(context).load(event.getEventImage()).into(imageView);
        return view;
    }
    private Event getEvent(int position){
        return (Event) getItem(position);
    }
}

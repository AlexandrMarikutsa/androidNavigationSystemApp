package com.demo.navigationsysapp.app.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.demo.navigationsysapp.app.R;
import com.demo.navigationsysapp.app.activities.pojo.Event;

import java.util.List;

public class EventAdapter extends BaseAdapter {
    private List<Event> events;
    private LayoutInflater layoutInflater;

    public EventAdapter(Context context, List<Event> events) {
        this.events = events;
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
            view = layoutInflater.inflate(R.layout.item_layout,parent,false);
        }
        Event event = getEvent(position);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(event.getType());
        return view;
    }

    private Event getEvent(int position){
        return (Event) getItem(position);
    }
}

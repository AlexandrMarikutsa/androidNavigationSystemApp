package com.demo.navigationsysapp.app.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.demo.navigationsysapp.app.R;
import com.demo.navigationsysapp.app.activities.pojo.Event;
import com.squareup.picasso.Picasso;

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

    static class ViewHolder {
        TextView eventName;
        TextView eventCreatedAt;
        ImageView eventAvatar;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Event getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){

            convertView = layoutInflater.inflate(R.layout.mylist,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.eventName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.eventCreatedAt = (TextView) convertView.findViewById(R.id.createdAt);
            viewHolder.eventAvatar = (ImageView) convertView.findViewById(R.id.avatar);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Event event =  getItem(position);

        viewHolder.eventName.setText(event.getRepo().getName());
        viewHolder.eventCreatedAt.setText(event.getType() + ' ' + event.getCreatedAt());
        Picasso.with(context).load(event.getRepo().getImageUrl()).into(viewHolder.eventAvatar);

        return convertView;
    }

}

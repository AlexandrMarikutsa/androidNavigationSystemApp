package com.demo.navigationsysapp.app.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.demo.navigationsysapp.app.R;
import com.demo.navigationsysapp.app.activities.pojo.User;

import java.util.List;

public class UserAdapter extends BaseAdapter{
    private List<User> users;
    private LayoutInflater layoutInflater;

    public UserAdapter(Context context, List<User> users) {
        this.users = users;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
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
        User user = getUser(position);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(user.getName());
        return view;
    }

    private User getUser(int position){
        return (User) getItem(position);
    }
}

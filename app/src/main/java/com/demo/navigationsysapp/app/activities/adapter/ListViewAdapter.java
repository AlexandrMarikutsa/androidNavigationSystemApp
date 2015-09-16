package com.demo.navigationsysapp.app.activities.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.demo.navigationsysapp.app.R;
import com.demo.navigationsysapp.app.activities.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;//--


    public List<User> users;
//    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    public ListViewAdapter(Context context,List<User> users){
        super();
//        this.activity=activity;
        this.users = users;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){

            view = layoutInflater.inflate(R.layout.colmn_row,parent,false);

            txtFirst=(TextView) view.findViewById(R.id.name);
            txtSecond=(TextView) view.findViewById(R.id.lastname);
         }

        User user =users.get(position);
        txtFirst.setText(user.getName());
        txtSecond.setText(user.getLastName());

        return view;
    }




}

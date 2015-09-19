package com.demo.navigationsysapp.app.activities.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.demo.navigationsysapp.app.R;
import com.squareup.picasso.Picasso;

public class EventActivity extends Activity{

    private ImageView event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_view);
        event = (ImageView) findViewById(R.id.event);
        Picasso.with(this).load(getIntent().getStringExtra("event")).into(event);
    }

    public void onClick(View view){
        onBackPressed();
        finish();
    }
}

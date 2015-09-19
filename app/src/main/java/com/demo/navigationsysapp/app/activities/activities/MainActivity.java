package com.demo.navigationsysapp.app.activities.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.demo.navigationsysapp.app.R;
import com.demo.navigationsysapp.app.activities.adapter.EventAdapter;
import com.demo.navigationsysapp.app.activities.pojo.Event;
import com.demo.navigationsysapp.app.activities.pojo.Repo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    public static String LOG_TAG = "my_log";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ParseTask().execute();


    }
    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://api.github.com/events");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            Log.d(LOG_TAG, strJson);

            try {
                JSONArray eventsJSON = new JSONArray(strJson);

                final List<Event> events = new ArrayList<Event>();
                for (int i = 0; i < eventsJSON.length(); i++) {
                    JSONObject eventJSON = eventsJSON.getJSONObject(i);
                    Event event = new Event(eventJSON.getString("type"));
                    event.setCreatedAt(eventJSON.getString("created_at"));
                    event.setRepo(new Repo(eventJSON.getJSONObject("repo").getString("name"),eventJSON.getJSONObject("repo").getString("url")));
                    events.add(event);
                }
                listView = (ListView) findViewById(R.id.listView);
                EventAdapter eventAdapter = new EventAdapter(getApplication(), events);
                listView.setAdapter(eventAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent(getApplication(), EventActivity.class);
                        intent.putExtra("event",events.get(+position).getRepo().getImageUrl());
                        startActivity(intent);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.demo.navigationsysapp.app.activities.activities;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.demo.navigationsysapp.app.R;
import com.demo.navigationsysapp.app.activities.adapter.EventAdapter;
import com.demo.navigationsysapp.app.activities.pojo.Event;
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

                List<Event> events = new ArrayList<Event>();
                for (int i = 0; i < eventsJSON.length(); i++) {
                    JSONObject eventJSON = eventsJSON.getJSONObject(i);
                    Event event = new Event(eventJSON.getString("type"));
                    event.setActorAvatar(eventJSON.getJSONObject("actor").getString("avatar_url"));
//                    Log.d(LOG_TAG, "type: " + event.getType());
                    events.add(event);
                }
                listView = (ListView) findViewById(R.id.listView);
                EventAdapter eventAdapter = new EventAdapter(getApplication(), events);
                listView.setAdapter(eventAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

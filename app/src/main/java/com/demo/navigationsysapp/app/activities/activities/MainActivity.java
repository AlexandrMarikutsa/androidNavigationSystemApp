package com.demo.navigationsysapp.app.activities.activities;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.demo.navigationsysapp.app.R;
import com.demo.navigationsysapp.app.activities.adapter.EventAdapter;
import com.demo.navigationsysapp.app.activities.adapter.ListViewAdapter;
import com.demo.navigationsysapp.app.activities.adapter.UserAdapter;
import com.demo.navigationsysapp.app.activities.pojo.Event;
import com.demo.navigationsysapp.app.activities.pojo.User;
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
                URL url = new URL("http://demo.codeofaninja.com/tutorials/json-example-with-php/index.php");

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
            Log.d(LOG_TAG,"-------  THIS IS HOLE JSON !!! ----------" + strJson);

            JSONObject dataJsonObj = null;
            String secondName = "";

            try {
                dataJsonObj = new JSONObject(strJson);
                JSONArray usersJSON = dataJsonObj.getJSONArray("Users");
//                JSONObject secondFriend = friends.getJSONObject(1);
//                secondName = secondFriend.getString("name");
//                Log.d(LOG_TAG, "Второе имя: " + secondName);

//                List<Event> events = new ArrayList<Event>();
                List<User> users = new ArrayList<User>();
                for (int i = 0; i < usersJSON.length(); i++) {
                    JSONObject userJSON = usersJSON.getJSONObject(i);
                    User user = new User(userJSON.getString("firstname"),userJSON.getString("lastname"));
//                    Long id = event.getId();
//                    String type = event.getType();
//                    Log.d(LOG_TAG, "id: " + id.toString());
                    Log.d(LOG_TAG, "user name: " + user.getName());
                    Log.d(LOG_TAG, "user lastname: " + user.getLastName());
                    users.add(user);
                }
                listView = (ListView) findViewById(R.id.listView);
//                EventAdapter eventAdapter = new EventAdapter(getApplication(), events);
//                UserAdapter userAdapter = new UserAdapter(getApplication(), users);
                ListViewAdapter listViewAdapter = new ListViewAdapter(getApplication(), users);
                listView.setAdapter(listViewAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

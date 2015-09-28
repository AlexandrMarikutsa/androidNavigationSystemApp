package com.demo.navigationsysapp.app.activities.pojo;


import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Repo {
    public static String URL = "url";
    public static String NAME = "name";


    private String name;
    private String imageUrl;
    private String repoUrl = null;
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = null;

    public Repo(String name, String repoUrl){
        this.name = name;
        this.repoUrl = repoUrl;
        new ParseTask().execute();
    }
    private class ParseTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(repoUrl);

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
            try {
                JSONObject resultJSON = new JSONObject(resultJson);
                imageUrl = resultJSON.getJSONObject("owner").getString("avatar_url");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return resultJson;

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

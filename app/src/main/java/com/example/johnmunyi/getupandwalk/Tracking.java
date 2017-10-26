package com.example.johnmunyi.getupandwalk;

import android.os.AsyncTask;

/**
 * Created by johnmunyi on 10/26/17.
 */

public class Tracking extends AsyncTask<String, String, String> {

    private String resp;

    @Override
    protected String doInBackground(String... params){
        try
        {
            int trackedTime = Integer.parseInt(params[0])*1000;
            Thread.sleep(trackedTime);
            resp = "Slept for " + params[0] + "seconds";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            resp = e.getMessage();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        // send notification to the screen
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}

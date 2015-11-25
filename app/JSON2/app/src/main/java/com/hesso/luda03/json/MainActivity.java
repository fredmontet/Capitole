package com.hesso.luda03.json;

import android.os.Bundle;
import android.os. AsyncTask;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class MainActivity extends Activity{
    private static String urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.tv);
        Button bouton = (Button) findViewById(R.id.btn);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                urlString = "http://api.themoviedb.org/3/search/movie?query=batman&api_key=db8ffc3b1f3f410e28843083b6f551a5";
                new ProcessJSON().execute(urlString);
            }
        });
    }

    private class ProcessJSON extends AsyncTask<String, Void, String>{

        //un ou plusieurs strings peuvent être passés en paramètre
        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream){
            TextView tv = (TextView) findViewById(R.id.tv);



            //..........Process JSON DATA................
            if(stream !=null){
                try{
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader= new JSONObject(stream);

                    // Get the JSONObject "results"...........................
                    JSONObject results = reader.getJSONObject("results");
                    // Get the value of key "orignal_title"
                    String lon = results.getString("\"original_title");


                    tv.setText(tv.getText()+ "\tresults...\n");
                    tv.setText(tv.getText()+ "\t\toriginal_title..."+ lon + "\n");

                }catch(JSONException e){
                    e.printStackTrace();
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end
} // Activity end
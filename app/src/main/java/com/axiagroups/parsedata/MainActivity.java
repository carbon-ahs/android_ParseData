package com.axiagroups.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;


public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    String GOOGLE_URL = "https://www.google.com";
    String apiUrl = "https://jsonplaceholder.typicode.com/todos";
    String jsonObjectApiUrl = "https://jsonplaceholder.typicode.com/todos/2";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, jsonObjectApiUrl, null,
                response -> {
                    try {
                        Log.d("Main", "onResponse:" + response.getString("title"));
                        textView.setText(response.getString("title").toString());

                    } catch (JSONException e) {
                        Log.d("Main", "onResponse: Failed to fetch API");
                        throw new RuntimeException(e);
                    }

                },
                error -> {Log.d("Main", "onResponse: Failed to fetch API");
                });
        requestQueue.add(jsonObjectRequest);
//
//        getString(requestQueue);
//        getJsonArrayRequest();
    }



    private void getJsonArrayRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                response -> {
//                    Log.d("Main", "onResponse:" + response);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("Main", "onResponse:" + jsonObject.getString("title"));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                error -> Log.d("Main", "onResponse: Failed to fetch API"));
        requestQueue.add(jsonArrayRequest);
    }

    private void getString(RequestQueue requestQueue) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GOOGLE_URL, response -> {
            Log.d("Main", "onResponse:" + response.substring(0, 20));
        }, error -> {
            Log.d("Main", "onResponse: Failed to fetch API");
        });
        requestQueue.add(stringRequest);
    }
}
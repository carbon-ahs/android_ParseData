package com.axiagroups.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    String GOOGLE_URL = "https://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GOOGLE_URL, response -> {
            Log.d("Main", "onResponse:" + response.substring(0, 20));
        }, error -> {
            Log.d("Main", "onResponse: Failed to fetch API");
        });
        requestQueue.add(stringRequest);
    }
}
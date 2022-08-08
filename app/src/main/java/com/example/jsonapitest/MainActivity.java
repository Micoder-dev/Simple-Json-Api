package com.example.jsonapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://micoder-dev.github.io/files/index.json",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("RESPONSEVALIDDATA", response + "");

                        try {

                            String mytext = response.getString("mytext");
                            Toast.makeText(MainActivity.this, mytext + "", Toast.LENGTH_LONG).show();

                            String myimage = response.getString("myimage");
                            Toast.makeText(MainActivity.this, myimage + "", Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Something went wrong, please check your internet connection", Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("RESPONSEERROR", error.getMessage());

                    }
                });

        mRequestQueue.add(jsonObjectRequest);


    }
}
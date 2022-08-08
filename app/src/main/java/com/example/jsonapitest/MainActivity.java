package com.example.jsonapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;

    private TextView myTextView;
    private ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.myText);
        myImageView = findViewById(R.id.myImage);

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
                            myTextView.setText(mytext);

                            String myimage = response.getString("myimage");
                            Toast.makeText(MainActivity.this, myimage + "", Toast.LENGTH_LONG).show();

                            Picasso.get().load(myimage).placeholder(R.drawable.ic_launcher_background).into(myImageView);

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
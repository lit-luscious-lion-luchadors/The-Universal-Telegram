package com.example.the_universal_telegram;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity{

    Button homeButton;
    TextView articleBody;
    TextView articleTitle;
    Button clearButton;
    ScrollView scrollView;

    private TextView mArticles;
    private Button requestArticles;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        scrollView = (ScrollView) findViewById(R.id.scrollView);

        homeButton  = (Button) findViewById(R.id.home);
        requestArticles  = (Button) findViewById(R.id.GetArticles);

        //articleBody = (TextView) findViewById(R.id.bodyText);

        mArticles = (TextView) findViewById(R.id.bodyText);

        articleTitle = (TextView) findViewById(R.id.title);

        clearButton = (Button) findViewById(R.id.clearArticles);


        mQueue = Volley.newRequestQueue (this);

        requestArticles.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                jsonPars();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if (mQueue != null) {
                    mQueue.cancelAll(this);
                }
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                //mArticles.setText("");
            }
        });

        clearButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                mArticles.setText("");
            }
        });
    }

    private void jsonPars() {
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=d8836231103a4e108eef3650ae1e8478";
        JsonObjectRequest request = new JsonObjectRequest (Request.Method.GET, url, null,
                new Response.Listener <JSONObject> ( ) {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray ("articles");

                            for(int i = 0; i < jsonArray.length (); i++){
                                JSONObject Articles = jsonArray.getJSONObject (i);

                                String title = Articles.getString ("title");
                                String description = Articles.getString ("description");
                                String urlToImage = Articles.getString ("urlToImage");
                                String content = Articles.getString("content");

                                mArticles.append(title + "\n\n" + description + "\n\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace ( );
                        }
                    }
                }, new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace ();
            }
        });
        mQueue.add(request);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);


        savedInstanceState.putString("mArticles",(String)mArticles.getText().toString());


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        mArticles.setText(savedInstanceState.getString("mArticles"));


    }



}

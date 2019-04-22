package com.example.the_universal_telegram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class PullingNewsFromJSON extends AppCompatActivity {

    private TextView mArticles;
    private Button requestArticles;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_pulling_news_from_json);

            mArticles = findViewById (R.id.ArticleResult);
            requestArticles = findViewById (R.id.GetArticles);

            mQueue = Volley.newRequestQueue (this);

            requestArticles.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    jsonPars();
                }
            });
        }


    private void jsonPars() {
        String url = "'https://newsapi.org/v2/top-headlines?country=us&apiKey=d8836231103a4e108eef3650ae1e8478";
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
}




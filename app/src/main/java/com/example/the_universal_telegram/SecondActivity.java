package com.example.the_universal_telegram;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity{

    Button homeButton;
    TextView articleBody;
    TextView articleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        homeButton  = (Button) findViewById(R.id.home);
        articleBody = (TextView) findViewById(R.id.bodyText);
        articleTitle = (TextView) findViewById(R.id.title);

        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("articleBody",(String)articleBody.getText().toString());
        savedInstanceState.putString("articleTitle",(String)articleTitle.getText().toString());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        articleBody.setText(savedInstanceState.getString("articleBody"));
        articleTitle.setText(savedInstanceState.getString("articleTitle"));

    }



}

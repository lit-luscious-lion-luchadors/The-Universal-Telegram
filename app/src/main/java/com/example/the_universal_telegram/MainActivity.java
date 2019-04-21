package com.example.the_universal_telegram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button articlePageButton;
    Button fireBaseTesterButton;
    Button homepageButton;
    Button headlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        articlePageButton  = (Button) findViewById(R.id.articlePageButton);
        fireBaseTesterButton = (Button) findViewById(R.id.fireBaseTestButton);
        homepageButton = (Button) findViewById(R.id.homepageButton);
        headlineButton = (Button) findViewById (R.id.Headlines);

        articlePageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        fireBaseTesterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, UpdatingFireBaseButtonActivity.class));
            }
        });

        homepageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, homePageActivity.class));
            }
        });

        headlineButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (MainActivity.this, PullingNewsFromJSON.class));
            }
        });

    }
}

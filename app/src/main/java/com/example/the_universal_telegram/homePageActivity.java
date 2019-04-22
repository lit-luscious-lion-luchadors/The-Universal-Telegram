package com.example.the_universal_telegram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class homePageActivity extends AppCompatActivity {

    Button returnToTopBtn;
    ScrollView articleScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        returnToTopBtn = (Button) findViewById(R.id.returnToTopBtn);
        articleScrollView = (ScrollView) findViewById(R.id.articleScrollView);

        returnToTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }
}

package com.example.the_universal_telegram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdatingFireBaseButtonActivity extends AppCompatActivity {

    Integer count;
    TextView counter;
    Button incrementer;
    Button resetter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCountDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebasetest);

        counter = (TextView) findViewById(R.id.counter);
        incrementer = (Button) findViewById(R.id.incrementer);
        resetter = (Button) findViewById(R.id.resetter);

        count = 0; // TODO: This has to change from =0 to being read from the database on creation. Setting to 0 defeats the purpose of a DB.
        // But the databse works! :)

        counter.setText(count.toString());

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCountDatabaseReference = mFirebaseDatabase.getReference().child("count");

        incrementer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                count = count + 1;
                mCountDatabaseReference.setValue(count);
                counter.setText(count.toString());
            }
        });

        resetter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                count = 0;
                mCountDatabaseReference.setValue(count);
                counter.setText(count.toString());
            }
        });
    }

}

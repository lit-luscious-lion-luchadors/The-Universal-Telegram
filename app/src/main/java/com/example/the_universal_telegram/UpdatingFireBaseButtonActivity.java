package com.example.the_universal_telegram;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UpdatingFireBaseButtonActivity extends AppCompatActivity {

    private TextView counter;
    private Button incrementer;
    private Button resetter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private int upVoteCount;

    private static final String TAG = "FirebaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebasetest);

        counter = (TextView) findViewById(R.id.counter);
        incrementer = (Button) findViewById(R.id.incrementer);
        resetter = (Button) findViewById(R.id.resetter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();



        incrementer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onUpVoted(mDatabaseReference);
                counter.setText(String.valueOf(upVoteCount));
            }
        });

        resetter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        ValueEventListener upVoteListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = "";
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                    value = childSnapshot.getValue().toString();
                }
                if (value != "")
                    upVoteCount = Integer.parseInt(value.replaceAll("[\\D]", ""));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addValueEventListener(upVoteListener);
    }

    private void onUpVoted(DatabaseReference postRef){
        postRef.runTransaction(new Transaction.Handler(){
            @Override
            public Transaction.Result doTransaction(MutableData mutableData){

               // Toast.makeText(UpdatingFireBaseButtonActivity.this, "inside onupvoted", Toast.LENGTH_SHORT).show();
                upVoteCount = upVoteCount +1;
                mDatabaseReference.child("up_votes").setValue(upVoteCount);
                //p.upVotes.put(getUid(),true);


                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot){
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }
/*  UNUSED CODE

    public class Post{
        private int upVoteCount;
        public String uid;
        public Map<String,Boolean> upVotes = new HashMap<>();


        public Post(String uid){
            this.uid = uid;
        }

        public void setUpVotes(int num){
            upVoteCount = num;
        }

        public int getUpVotes(){
            return upVoteCount;
        }

        @Exclude
        public Map<String,Object> toMap(){
            HashMap<String,Object> result = new HashMap<>();
            result.put("uid", uid);
            result.put("upVotes", upVotes);
            result.put("upVoteCount", upVoteCount);

            return result;
        }
    }

    public String getUid(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
*/

}

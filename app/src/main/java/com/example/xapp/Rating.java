package com.example.xapp;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Rating extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Teacher");

    RatingBar ratingbar;
    Button button;
    String rithu;
    int sum,rate,rating,num_ppl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        addListenerOnButtonClick();

    }
    public void addListenerOnButtonClick(){
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        button=(Button)findViewById(R.id.button);
        //Performing action on Button Click
        button.setOnClickListener(v -> {




            //Getting the rating and displaying it on the toast
             rating=(int)ratingbar.getRating();
            Bundle extras= getIntent().getExtras();
            if(extras!=null) {
                rithu = extras.getString("MY_KEY");
                // Log.i("safwan",rithu); }
            }

            myRef.child(rithu).child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds:dataSnapshot.getChildren()) {
                        rate = Integer.parseInt(ds.getValue().toString());
                        num_ppl = Integer.parseInt(ds.getKey());
                    }
                    Toast.makeText(getApplicationContext(), rating+" is the rating", Toast.LENGTH_LONG).show();
                        sum=rate*num_ppl;
                        rating=sum+rating;
                        num_ppl=num_ppl+1;
                        rate=rating/num_ppl;
                        Log.i("pranav",rate+" "+num_ppl);
                        myRef.child(rithu).child("rating").child((num_ppl-1)+"").removeValue();
                        myRef.child(rithu).child("rating").child(num_ppl+"").setValue(rate);

                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        });
    }
}

package com.example.xapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseError;

import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {
    ImageView send;
    SharedPreferences sharedpreference;
    public static final String mypreference = "myprefer";
    Context context;
    public static final String tid = "tid";
    String teacherid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        addlisteneronbutton();
    }
    public void addlisteneronbutton()
    {
        send=(ImageView)findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreference = context.getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
             teacherid   =sharedpreference.getString(tid,"heeh");
                Log.i("studentss",teacherid);


            }
        });

    }

}


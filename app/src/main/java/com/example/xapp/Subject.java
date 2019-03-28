package com.example.xapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Subject extends AppCompatActivity {
    ImageButton s,m,i;
    SharedPreferences sharedpreferences;
    TextView name;
    TextView email;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Get();
        addListenerOnButton();
    }
    public void addListenerOnButton()
    {
        final Context context= this;
        s=(ImageButton)findViewById(R.id.science);
        m=(ImageButton)findViewById(R.id.maths);
        i=(ImageButton)findViewById(R.id.inbox);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, Science.class);
                startActivity(intent);
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, Maths.class);
                startActivity(intent);
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,Users.class);
                startActivity(intent);
            }
        });
    }
    public void Get() {
        name = (TextView) findViewById(R.id.name);
        //email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
            name.setText(sharedpreferences.getString(Name, ""));
       // if (sharedpreferences.contains(Email)) {
         //   email.setText(sharedpreferences.getString(Email, ""));

        }
    }


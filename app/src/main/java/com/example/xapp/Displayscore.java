package com.example.xapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Displayscore extends AppCompatActivity {
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayscore);

        Bundle extras= getIntent().getExtras();
        if( extras!=null)
        {
            msg = extras.getString("MY_KEY");

        }


        TextView textView = (TextView) findViewById(R.id.score);

        textView.setText(msg );
        textView.setTextColor(Color.BLUE);
    }
}


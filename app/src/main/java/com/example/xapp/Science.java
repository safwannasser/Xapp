package com.example.xapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Science extends AppCompatActivity {
    Button d, p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        final Context context = this;
        d = (Button) findViewById(R.id.ds);
        p = (Button) findViewById(R.id.ps);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, Digestive.class);
                startActivity(intent);
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Photosyn.class);
                startActivity(intent);
            }
        });
    }
}

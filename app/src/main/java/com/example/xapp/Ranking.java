package com.example.xapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Ranking extends AppCompatActivity {
    ListAdapter listAdapter;
    Context context;
    Subject sub=(Subject)context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

    }

    @Override
    public void onBackPressed() {
        if(listAdapter.class_sub==1)
        {
            finish();
        }

        else if (listAdapter.class_sub==2) {
            sub.setClasses();
        }
        else if(listAdapter.class_sub==3)
        {
            sub.setSubjects(listAdapter.classnum);

        }


    }

    void toDigest()
    {

    }
    void tophoto()
    {

    }
}

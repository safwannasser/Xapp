package com.example.xapp;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Hangman extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        Button playBtn = (Button)findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.playBtn) {
            Intent playIntent = new Intent(this, GameActivity.class);
            this.startActivity(playIntent);
        }
    }
}

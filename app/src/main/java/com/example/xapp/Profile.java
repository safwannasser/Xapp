package com.example.xapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        Button r,score;
        ImageView qr_code_button;
        String getemail;

  //   public static final String ID = "sid";
    TextView Student_n,std;

  String sid,Sn;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addListenerOnButton();




        Bundle extras= getIntent().getExtras();
        if(extras!=null)
        {
            sid=extras.getString("MY_KEY");}
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Students");
        myRef.child(sid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Sn=dataSnapshot.child("name").getValue().toString();
                Log.i("sname",Sn);
                Student_n = findViewById(R.id.student_name);
                std=findViewById(R.id.Stdname);
                std.setText(Sn);
                //email = (TextView) findViewById(R.id.etEmail);

                Student_n.setText(Sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Profile.this,Users.class);


                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }
*/
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.edit_profile) {

        } else*/ if (id == R.id.logout) {
            Intent intent=new Intent(this,login.class);
            startActivity(intent);


        } /*else if (id == R.id.nav_share) {

        }*/ /*else if (id == R.id.tnc) {
            Intent intent=new Intent(this,TermsConditions.class);
            startActivity(intent);

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addListenerOnButton()
    {

        r=findViewById(R.id.button);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Subject.class);
                startActivity(intent);
            }
        });
        score=findViewById(R.id.rank);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,Ranking.class);
                startActivity(intent);
            }
        });


        //qrcode

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        qr_code_button = findViewById(R.id.qrcode_imageview);
        qr_code_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initiating the qr code scan
                qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });


    }
  /*  public Void get()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Students");
       String Sn= myRef.child(sid).child("name").getKey();

        Student_n = (TextView) findViewById(R.id.student_name);
        //email = (TextView) findViewById(R.id.etEmail);

        Student_n.setText(sharedpreferences.getString(Sn, ""));

        // if (sharedpreferences.contains(Email)) {
        //   email.setText(sharedpreferences.getString(Email, ""));
    }
    */



    //Getting the scan results

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data

                //converting the data to json
//                    JSONObject obj = new JSONObject(result.getContents());

                String scanned_str = result.getContents();
                   /* //setting values to textviews
                    textViewName.setText(obj.getString("name"));
                    textViewAddress.setText(obj.getString("address"));*/

                Intent intent = new Intent(Profile.this,Digestive.class);
                startActivity(intent);


//                textViewName.setText(scanned_str);
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
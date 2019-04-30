package com.example.xapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Subject extends AppCompatActivity {
    // ImageButton s, m, i;
    FloatingActionButton i;
    //SharedPreferences sharedpreferences;
    TextView name;
    TextView email;
   // public static final String mypreference = "mypref";
   // public static final String Name = "nameKey";
   // public static final String Email = "emailKey";
    ArrayList<Classes> classes;
    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        getSubjects();
       /* Get();*/
        //addListenerOnButton();
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
               *//* i=(FloatingActionButton)findViewById(R.id.inbox);
                i.setOnClickListener(v -> {*//*
                    Intent intent=new Intent(Subject.this,Users.class);
                    startActivity(intent);
            };
        });*/
        };






    @Override
    public void onBackPressed() {
        if(listAdapter.class_sub==1)
        {
            finish();
        }

        else if (listAdapter.class_sub==2) {
            setClasses();
        }
        else if(listAdapter.class_sub==3)
        {
            setSubjects(listAdapter.classnum);

        }


    }



    void getSubjects()
    {
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CLASS");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classes=new ArrayList<Classes>();

                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    Classes cls = new Classes();
                    cls.clss = ds.getKey();
                    cls.subs=new ArrayList<Classes.Subjects>();
                    if(ds.getValue().toString().equals("Under Maintanence")) {
                        cls.status = "xa";
                    }
                    else{
                        cls.status="ahjk";
                        for(DataSnapshot ds1:ds.getChildren()) {
                            Classes.Subjects sub = new Classes.Subjects();
                            sub.name = ds1.getKey();
                            cls.subs.add(sub);
                            if(ds1.getValue().toString().equals("Under Maintanence")) {
                                cls.subs.get(cls.subs.size()-1).status1="Under Maintanence";
                            }
                            else
                            {
                                cls.subs.get(cls.subs.size()-1).topics=new ArrayList<Classes.Subjects.Topics>();
                                for (DataSnapshot ds2:ds1.getChildren()){
                                    Classes.Subjects.Topics t = new Classes.Subjects.Topics();
                                    t.topic = ds2.getKey();
                                    cls.subs.get(cls.subs.size()-1).topics.add(t);
                                }
                            }
                            // cls.subs.add(sub);
                        }

                    }
                    classes.add(cls);

                }
                setClasses();


            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    void setList()
    {

    }
    void setClasses()
    {
        listAdapter=new ListAdapter(classes,Subject.this,1);
        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(listAdapter);
    }
    void setSubjects(int clss)
    {
        listAdapter.setSubjectData(classes.get (clss).subs,clss);
        listAdapter.notifyDataSetChanged();
        //  listView.setAdapter(listAdapter);
    }

    void setTopics(int clss, int subnum)
    {
        listAdapter.setTopicData(classes.get(clss).subs.get(subnum).topics,subnum);
        listAdapter.notifyDataSetChanged();

    }

    void toDigest()
    {
        Intent intent=new Intent(this,Digestive.class);
        startActivity(intent);
    }
    void tophoto()
    {
        Intent intent=new Intent(this,Photosyn.class);
        startActivity(intent);
    }

   /* public void addListenerOnButton()
    {
        final Context context= this;
        i=(FloatingActionButton)findViewById(R.id.inbox);
        i.setOnClickListener(v -> {
        Intent intent=new Intent(this,Users.class);
        startActivity(intent);
        });



    }*/
 /*   public void Get() {
        name = (TextView) findViewById(R.id.name);
        //email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        name.setText(sharedpreferences.getString(Name, ""));
        // if (sharedpreferences.contains(Email)) {
        //   email.setText(sharedpreferences.getString(Email, ""));

    }*/
}



package com.example.xapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Digestive_rank extends AppCompatActivity {
        ListView dlist;
        TextView username;
    SharedPreferences sharedpreferences;
    public static final String mypreferences = "myprefer";
    public static final String ID = "sid";
    ArrayList<String> a2 = new ArrayList<>();
    final ArrayList<String> list_d = new ArrayList<String>();
    ProgressDialog pd;
    ArrayList<Studentname> students;
    Context context;
    String studentsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digestive_rank);
        username=findViewById(R.id.studentn);
        dlist=findViewById(R.id.S_list);
        sharedpreferences=context.getSharedPreferences(mypreferences, Context.MODE_PRIVATE);
        studentsid=sharedpreferences.getString(ID,"hah");
        listennames();

    }
    void listennames()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("Students");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                students=new ArrayList<Studentname>();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    Studentname newname=new Studentname();
                    newname.name=ds.child("name").getValue().toString();
                    newname.rank=Integer.parseInt(ds.child("ranking").child("digestive_system").getValue().toString());
                    students.add(newname);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

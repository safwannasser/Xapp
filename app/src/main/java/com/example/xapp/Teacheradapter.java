package com.example.xapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teacheradapter extends BaseAdapter {
    SharedPreferences sharedpreferences;
    SharedPreferences sharedpreference;
 ArrayList<Teachername> teachers;
 Context context;
 String key,sid,sname,studentid,semail;
    public static final String ID = "sid";
 int flag=0;
    public static final String mypreferences = "myprefer";
   // public static final String Name = "nameKey";
    //public static final String Email = "emailKey";
    public static final String mypreference = "myprefer";

    public static final String tid = "tid";
 Teacheradapter(ArrayList<Teachername> teachers,Context context)
 {

     this.teachers=teachers;
     this.context=context;
 }

 @Override
    public int getCount(){
     return teachers.size();
 }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.teacher_list,parent,false);
        TextView textView=(TextView)convertView.findViewById(R.id.name);
        textView.setText(teachers.get(position).name);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String tname=teachers.get(position).name;

                sharedpreferences = context.getSharedPreferences(mypreferences,
                        Context.MODE_PRIVATE);
                sharedpreference = context.getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
                sid=sharedpreferences.getString(ID,"hahaha  "+tname);
                Log.i("student",sid);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Students");
                DatabaseReference ref= database.getReference("Teacher");


                final Query userquery = FirebaseDatabase.getInstance().getReference().child("Teacher").orderByChild("name");

                userquery.equalTo(tname).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot teachersnapshot : dataSnapshot.getChildren())
                                {
                                     key= teachersnapshot.getKey();
                                    Log.i("rithuuu",key);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(tid,key);
                                    editor.commit();
                                    Users log = (Users) context;
                                    log.tochat();


                                    /*ref.child(key).child("chats").child(sid).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Log.e("Pranav"," ahhaha"+dataSnapshot);

                                            if (dataSnapshot.exists())
                                                flag = 1;


                                            if (flag == 1) {
                                                Users log = (Users) context;
                                                log.tochat();

                                                //chat exist
                                            } else {
                                                //chat doesn't exit
                                                ref.child(key).child("chats").child(sid).child("message1").child("id").setValue("0");
                                                //Users log = (Users) context;
                                                //log.tochat();

                                              //  ref.child(key).child("chats").child(sid).child("message1").child("message").setValue("hi");


                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });*/

                                }


                            }
                            //DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("Teacher").child(key).child("chats");

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        }
                 );




            }
        });
        return convertView;
    }
}

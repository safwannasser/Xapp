package com.example.xapp;

import android.content.Context;
import android.content.SharedPreferences;
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
 ArrayList<Teachername> teachers;
 Context context;
 String key,sid,sname,studentid;
 int flag=0;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
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
                sharedpreferences = context.getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
                sname=sharedpreferences.getString(Name,"hahaha  "+tname);
                Log.i("student",sname);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Students");
                DatabaseReference ref= database.getReference("Teacher");

                        final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Students").orderByChild("name");
                      //  Log.i("tevin",userQuery+"");
                        userQuery.equalTo(sname).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Log.i("tevin",dataSnapshot+"");

                                for(DataSnapshot studentsnapshot : dataSnapshot.getChildren())
                                {
                                    sid=studentsnapshot.getKey();
                                    Log.i("safwaaa",sid);
                                }
                            }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                final Query userquery = FirebaseDatabase.getInstance().getReference().child("Teacher").orderByChild("name");

                userquery.equalTo(tname).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot teachersnapshot : dataSnapshot.getChildren())
                                {
                                     key= teachersnapshot.getKey();
                                    Log.i("rithuuu",key);
                                }

                            }
                            //DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("Teacher").child(key).child("chats");

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        }
                 );
                ref.child("chats").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {
                            studentid=ds.getValue().toString();
                            if(sid==studentid)
                            {
                                flag=1;
                            }


                        }
                        if(flag==1)
                        {
                            //chat exist
                        }
                        else
                        {
                            //chat doesn't exit
                            ref.child(key).child("chats").child(sid).child("message1").child("id").setValue("0");
                           ref.child(key).child("chats").child(sid).child("message1").child("message").setValue("hi");


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });
        return convertView;
    }
}

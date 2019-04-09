package com.example.xapp;

import android.content.Context;
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
 ArrayList<Teachername> teachers;
 Context context;
 String key,sid;
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
                final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Teacher").orderByChild("name");

                userQuery.equalTo(tname).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot teachersnapshot : dataSnapshot.getChildren())
                                {
                                     key= teachersnapshot.getKey();
                                    Log.i("UID",key);
                                }

                            }
                            DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("Teacher").child(key).child("chats");
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference userRef= users.child("Teacher").child(key).child("chats");



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

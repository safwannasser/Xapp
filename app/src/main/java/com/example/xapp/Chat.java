package com.example.xapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {
    ImageView send;
    SharedPreferences sharedpreference;
    SharedPreferences sharedpreferences;
    public static final String mypreferences = "myprefer";
    public static final String mypreference = "myprefer";
    int total_number;
    Context context;
    public static final String tid = "tid";
    public static final String ID = "sid";
    String teacherid,studentsid,ts;
    ArrayList<Messages> msglist;
    ChatAdapter chatAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        addlisteneronbutton();
        listenMsgs();
        sharedpreference = context.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        sharedpreferences=context.getSharedPreferences(mypreferences,Context.MODE_PRIVATE);
        teacherid   =sharedpreference.getString(tid,"heeh");
        Log.i("studentss",teacherid);
        studentsid=sharedpreferences.getString(ID,"hah");
        ts=teacherid+studentsid;
    }


    void listenMsgs()
    { ListView message_list;
        EditText msg;
        message_list=findViewById(R.id.mlist);
        msg=findViewById(R.id.messageArea);




        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("Chats");
        ref.child(ts).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {        msglist=new ArrayList<Messages>();
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {

                            Messages newone=new Messages();
                            newone.msgtxt=ds.child("msg_text").getValue().toString();
                            newone.timestamp=ds.child("mgs_timestamp").getValue().toString();
                            newone.timestamp=ds.child("msg_sender").getValue().toString();
                            msglist.add(newone);

                        }
                    if (chatAdapter == null) {
                        chatAdapter = new ChatAdapter(Chat.this, msglist);
                        message_list.setAdapter(chatAdapter);
                    } else {
                        chatAdapter.getData(msglist);
                        chatAdapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    Toast.makeText(Chat.this, getString(R.string.nochat), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void addlisteneronbutton()
    {
        send=(ImageView)findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myref=database.getReference("Chats");
                myref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {

                            total_number = Integer.parseInt(dataSnapshot.child(ts).child("tot_num").getValue().toString());
                            total_number=total_number+1;
                        }
                        else
                        {
                            total_number=1;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                myref.child(ts).child("tot_num").setValue(total_number);
            }
        });

    }

}


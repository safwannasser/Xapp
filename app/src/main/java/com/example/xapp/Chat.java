package com.example.xapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Chat extends AppCompatActivity {
    ImageView send;
    SharedPreferences sharedpreference;
    SharedPreferences sharedpreferences;
    public static final String mypreferences = "myprefer";
    public static final String mypreference = "myprefer";
    int total_number,flag=0;
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
        sharedpreference = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        sharedpreferences=getSharedPreferences(mypreferences,Context.MODE_PRIVATE);
        teacherid   =sharedpreference.getString(tid,"heeh");
        Log.i("studentss",teacherid);
        studentsid=sharedpreferences.getString(ID,"hah");
        Log.i("studentname",studentsid);
        ts=teacherid+studentsid;
        msgTriggerListen();
    }


    void listenMsgs()
    { ListView message_list;
        EditText msg;
        message_list=findViewById(R.id.mlist);
        msg=findViewById(R.id.messageArea);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("Chats");
        ref.child(ts).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    msglist=new ArrayList<Messages>();
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            if(!ds.getKey().equals("tot_num")&&!ds.getKey().equals("msg_trigger")) {
                                Messages newone = new Messages();
                                newone.msg_txt = ds.child("msg_txt").getValue().toString();

                               // newone.timestamp = ds.child("msg_timestamp").getValue().toString();
                                newone.msg_sender = ds.child("msg_sender").getValue().toString();

                                msglist.add(newone);
                            }
                            String ms_sender=ds.child("msg_sender").getValue(String.class);
                            ms_sender=ms_sender+"";
                            Log.d("Test msg sender",ms_sender);
                            if (ms_sender.equals("t")){
                                flag=1;
                            }

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
    void msgTriggerListen()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("Chats");
        ref.child(ts).child("msg_trigger").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listenMsgs();
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
                EditText msgs;
                msgs=findViewById(R.id.messageArea);

                String m=msgs.getText().toString();
                msgs.setText("");
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
                        myref.child(ts).child("tot_num").setValue(total_number);

                        myref.child(ts).child(total_number+"").child("msg_txt").setValue(m);
                        myref.child(ts).child(total_number+"").setValue(new Messages(m,"s"));
                        myref.child(ts).child("msg_trigger").setValue(new Date()+"");
                        listenMsgs();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
   public void onBackPressed()
    {
        if(flag==1)
        {
            LinearLayout l;
            l=findViewById(R.id.Dialog);
            l.setVisibility(View.VISIBLE);
            EditText feed;
            Button subm,later;
            feed=findViewById(R.id.feedback);
            subm=findViewById(R.id.Submit);
            later=findViewById(R.id.Notnow);
            subm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String info=feed.getText().toString();
                    feed.setText("");
                    if(info.equals(""))
                        Toast.makeText(Chat.this,"Enter feedback and submit" , Toast.LENGTH_LONG).show();

                }
            });


        }
        else
            super.onBackPressed();

    }

}


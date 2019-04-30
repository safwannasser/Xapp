package com.example.xapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Users extends AppCompatActivity {
    int tcount=0;
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    final ArrayList<String> list = new ArrayList<String>();
    int totalUsers = 0;
    ProgressDialog pd;
    ArrayList<com.example.xapp.Teachername> teachers;
    Teacheradapter teacheradapter;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);


        noUsersText = (TextView)findViewById(R.id.username);
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Teacher");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               teachers= new ArrayList<Teachername>();
                tcount = (int) dataSnapshot.getChildrenCount();
                int i=0;
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    Teachername teach= new Teachername();

                   String tname= ds.child("name").getValue().toString();
                    teach.name=tname;
                    teachers.add(teach);

                   // Log.e("name haallo",tname);

                   // list.add(i,tname);

                  //  i++;


                }
                setteachers();
               /* for(int l=tcount-1;l>=0;l--)
                {
                    Log.i("list",list.get(l));
                }*/


               /*     LinearLayout tlist= (LinearLayout) findViewById(R.id.linear);
//        tlist.removeAllViews();
                for(int l=tcount-1;l>=0;l--)
                {
                    TextView teacher_name = new TextView(Users.this);
                    teacher_name.setTextSize(25);
                    teacher_name.setPadding(25, 5, 0, 0);

                    String teacher  = list.get(l);

                    teacher_name.setText(teacher);
                    tlist.addView(teacher_name);


                }*/









            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



        /*pd = new ProgressDialog(Users.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://xapp-b979f.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Users.this);
        rQueue.add(request);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position);
                startActivity(new Intent(Users.this, Chat.class));
            }
        });*/
    }
    void tochat()
    {
        Intent intent=new Intent(this,Chat.class);
        startActivity(intent);
    }
    void setteachers()
    {
        teacheradapter=new Teacheradapter(teachers,Users.this);
        Bundle extras= getIntent().getExtras();
        if(extras!=null)
        {
            email=extras.getString("MY_KEY");
            Log.i("safwan",email); }
        usersList = (ListView)findViewById(R.id.Userlist);

        usersList.setAdapter(teacheradapter);

    }

  /*  public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(UserDetails.username)) {
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <=1){
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
        }

        pd.dismiss();
    }*/


}
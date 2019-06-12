package com.example.xapp;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;


public class Quiz extends Activity {
    SharedPreferences sharedpreferences;
    ArrayList<Questions> questions;
    public static final String mypreferences = "myprefer";
    public static final String ID = "sid";
    String studentsid,topic,Flag,newword;
    int score=0,flag1=0,flag2=0,flag3=0;
    Integer totaltest;
    RadioGroup r1, r2, r3, r4, r5;
    Button button1, button2, button3, button4, button5, end;
    RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12, rb13, rb14, rb15,wrong;

    public ArrayList<String> words;
    TextView first,second,third;
    ArrayList<String> newans;
    ArrayList<Boolean> newcorrect;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Bundle extras= getIntent().getExtras();
        if(extras!=null)
        {
            Flag=(extras.getString("Flags"));
        }
        if(Flag!=null)
        {
            topic="DIGESTIVE SYSTEM";
        }
        else
        {
            topic="ELEMENTS";
        }


        button1 = (Button) findViewById(R.id.button1);
        r1 = (RadioGroup) findViewById(R.id.fans);


        button2= (Button) findViewById(R.id.button2);
        r2 = (RadioGroup) findViewById(R.id.sans);

        button3 = (Button) findViewById(R.id.button3);
        r3 = (RadioGroup) findViewById(R.id.tans);

       /* button4= (Button) findViewById(R.id.button4);
        r4 = (RadioGroup) findViewById(R.id.fourans);

        button5 = (Button) findViewById(R.id.button5);
        r5 = (RadioGroup) findViewById(R.id.fifans);

*/
       DatabaseReference databaseReference= database.getReference("CLASS").child("5").child("SCIENCE").child(topic).child("Quiz");
       databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               words=new ArrayList<String>();
               questions=new ArrayList<Questions>();
               for(DataSnapshot ds:dataSnapshot.getChildren())
               {
                   Questions ques=new Questions();
                   ques.ans=new ArrayList<>();
                   ques.correct=new ArrayList<>();
                   ques.question=ds.getKey();
                   //dfghf
                   for(DataSnapshot ds2:ds.getChildren())
                   {
                       ques.ans.add(ds2.getKey());
                       if(ds2.getValue().toString().equals("Y"))
                       {
                           ques.correct.add(true);
                           Log.e("Options","Options="+ds2.getKey()+"  ANs="+true);
                       }
                       else {
                           ques.correct.add(false);
                           Log.e("Options","Options="+ds2.getKey()+"  ANs="+false);
                       }
                       }
                   questions.add(ques);

               }addbuttonlistener();

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       addListenerOnButton();











/*

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag=4;
                if(r4.getCheckedRadioButtonId()== -1){
                    Toast.makeText(Quiz.this, "CHOOSE A VALID OPTION" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (R.id.b12==(r4.getCheckedRadioButtonId()))
                    {
                        score=score+1;
                        rb12 = (RadioButton) findViewById(R.id.b12);
                        rb12.setTextColor(Color.GREEN);
                        rb10 = (RadioButton) findViewById(R.id.b10);
                        rb10.setTextColor(Color.BLACK);
                        rb11 = (RadioButton) findViewById(R.id.b11);
                        rb11.setTextColor(Color.BLACK);
                        //   Toast.makeText(MainActivity.this, "RIGHT ANSWER!!" , Toast.LENGTH_LONG).show();
                    }
                    else {
                        int selectedId = r4.getCheckedRadioButtonId();

                        wrong = (RadioButton) findViewById(R.id.b10);
                        wrong.setTextColor(Color.RED);
                        rb12 = (RadioButton) findViewById(R.id.b12);
                        rb12.setTextColor(Color.GREEN);

                        if(selectedId== R.id.b10)
                        {
                            rb11 = (RadioButton) findViewById(R.id.b11);
                            rb11.setTextColor(Color.BLACK);

                        }
                        else
                        {
                            rb10 = (RadioButton) findViewById(R.id.b10);
                            rb10.setTextColor(Color.BLACK);
                        }

                        // Toast.makeText(MainActivity.this, "OOPS! WRONG ANSWER" , Toast.LENGTH_LONG).show();
                    }
                    if(flag==4){
                        button4.setEnabled(false);
                        addListenerOnButton();
                    }
                }}
        });
*/






       /* button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag=5;
                if(r5.getCheckedRadioButtonId()== -1){
                    Toast.makeText(Quiz.this, "CHOOSE A VALID OPTION" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (R.id.b15==(r5.getCheckedRadioButtonId()))
                    {
                        score=score+1;
                        rb15 = (RadioButton) findViewById(R.id.b15);
                        rb15.setTextColor(Color.GREEN);
                        rb13 = (RadioButton) findViewById(R.id.b13);
                        rb13.setTextColor(Color.BLACK);
                        rb14 = (RadioButton) findViewById(R.id.b14);
                        rb14.setTextColor(Color.BLACK);
                        //  Toast.makeText(MainActivity.this, "RIGHT ANSWER!!" , Toast.LENGTH_LONG).show();
                    }
                    else {
                        int selectedId = r5.getCheckedRadioButtonId();

                        wrong = (RadioButton) findViewById(R.id.b14);
                        wrong.setTextColor(Color.RED);
                        rb15 = (RadioButton) findViewById(R.id.b15);
                        rb15.setTextColor(Color.GREEN);

                        if(selectedId== R.id.b14)
                        {
                            rb13 = (RadioButton) findViewById(R.id.b13);
                            rb13.setTextColor(Color.BLACK);

                        }
                        else
                        {
                            rb14 = (RadioButton) findViewById(R.id.b14);
                            rb14.setTextColor(Color.BLACK);
                        }

                        //  Toast.makeText(MainActivity.this, "OOPS! WRONG ANSWER" , Toast.LENGTH_LONG).show();
                    }
                    if(flag==5){
                        button5.setEnabled(false);
                        addListenerOnButton();
                    }

                }}
        });*/




    }
    public void addbuttonlistener()
    {   Random rand= new Random();
        int random1=rand.nextInt(questions.size());
        newword=questions.get(random1).question;
        newans=questions.get(random1).ans;
        newcorrect=questions.get(random1).correct;
        first=(TextView)findViewById(R.id.fquestion);
        String newquestion=newword;
        first.setText(newword);
        for(int i=0;i<3;i++)
        {
            switch (i)
            {
                case 0:rb2 = (RadioButton) findViewById(R.id.b2);
                        rb2.setTag(newcorrect.get(i));
                         rb2.setText(newans.get(i));
                            break;
                case 1 : rb1 = (RadioButton) findViewById(R.id.b1);
                rb1.setTag(newcorrect.get(i));
                rb1.setText(newans.get(i));
                break;
                case 2: rb3 = (RadioButton) findViewById(R.id.b3);
                rb3.setTag(newcorrect.get(i));
                rb3.setText(newans.get(i));
                break;
            }
        }


        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag1=1;
                if(r1.getCheckedRadioButtonId()== -1){
                    Toast.makeText(Quiz.this, "CHOOSE A VALID OPTION" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    RadioButton choosed_option=(RadioButton)findViewById(r1.getCheckedRadioButtonId());
                    Log.e("Selected option","Option="+choosed_option.getText().toString()+"  ANs="+choosed_option.getTag());
                    if((Boolean) choosed_option.getTag())
                    {
                        score=score+1;

                        rb2=(RadioButton)findViewById(r1.getCheckedRadioButtonId());
                        rb2.setTextColor(Color.GREEN);

                    }

                    else{
                        rb2=(RadioButton)findViewById(r1.getCheckedRadioButtonId());
                        rb2.setTextColor(Color.RED);

                    }
                    if(flag1==1){
                        button1.setEnabled(false);
                        addListenerOnButton();
                    }
                }}
        });
        do {
           int  random = rand.nextInt(questions.size());
            newword = questions.get(random).question;
            newans = questions.get(random).ans;
            newcorrect = questions.get(random).correct;
        }while (newquestion==newword);
        second=(TextView)findViewById(R.id.squestion);
        String newquestion1=newword;
        second.setText(newword);
        for(int i=0;i<3;i++)
        {
            switch (i)
            {
                case 0:rb4 = (RadioButton) findViewById(R.id.b4);
                    rb4.setTag(newcorrect.get(i));
                    rb4.setText(newans.get(i));
                    break;
                case 1 : rb5 = (RadioButton) findViewById(R.id.b5);
                    rb5.setTag(newcorrect.get(i));
                    rb5.setText(newans.get(i));
                    break;
                case 2: rb6 = (RadioButton) findViewById(R.id.b6);
                    rb6.setTag(newcorrect.get(i));
                    rb6.setText(newans.get(i));
                    break;
            }
        }



        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag2=1;
                if(r2.getCheckedRadioButtonId()== -1){
                    Toast.makeText(Quiz.this, "CHOOSE A VALID OPTION" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    View choosed_option=findViewById(r2.getCheckedRadioButtonId());
                    if((Boolean) choosed_option.getTag())
                    {
                        score=score+1;

                        rb2=(RadioButton)findViewById(r2.getCheckedRadioButtonId());
                        rb2.setTextColor(Color.GREEN);

                    }

                    else{
                        rb2=(RadioButton)findViewById(r2.getCheckedRadioButtonId());
                        rb2.setTextColor(Color.RED);

                    }
                    if(flag2==1){
                        button2.setEnabled(false);
                        addListenerOnButton();
                    }
                }}
        });
        do {
            int random = rand.nextInt(questions.size());
            newword = questions.get(random).question;
            newans = questions.get(random).ans;
            newcorrect = questions.get(random).correct;
        }while ((newquestion==newword) || (newquestion1==newword));
        third=(TextView)findViewById(R.id.tquestion);

        third.setText(newword);
        for(int i=0;i<3;i++)
        {
            switch (i)
            {
                case 0:rb7 = (RadioButton) findViewById(R.id.b7);
                    rb7.setTag(newcorrect.get(i));
                    rb7.setText(newans.get(i));
                    break;
                case 1 : rb8 = (RadioButton) findViewById(R.id.b8);
                    rb8.setTag(newcorrect.get(i));
                    rb8.setText(newans.get(i));
                    break;
                case 2: rb9 = (RadioButton) findViewById(R.id.b9);
                    rb9.setTag(newcorrect.get(i));
                    rb9.setText(newans.get(i));
                    break;
            }
        }



        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag3=1;
                if(r3.getCheckedRadioButtonId()== -1){
                    Toast.makeText(Quiz.this, "CHOOSE A VALID OPTION" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    View choosed_option=findViewById(r3.getCheckedRadioButtonId());
                    if((Boolean) choosed_option.getTag())
                    {
                        score=score+1;

                        rb2=(RadioButton)findViewById(r3.getCheckedRadioButtonId());
                        rb2.setTextColor(Color.GREEN);

                    }

                    else{
                        rb2=(RadioButton)findViewById(r3.getCheckedRadioButtonId());
                        rb2.setTextColor(Color.RED);

                    }
                    if(flag3==1){
                        button3.setEnabled(false);
                        addListenerOnButton();
                    }
                }}
        });


    }
  /*  public void playquiz()
    {   int random=rand.nextInt(questions.size());
         newword=questions.get(random).question;
         newans=questions.get(random).ans;
         newcorrect=questions.get(random).correct;
        addbuttonlistener();


    }*/


    public void addListenerOnButton() {

        final Context context = this;
        final String str1 = Integer.toString(score);
        sharedpreferences=context.getSharedPreferences(mypreferences,Context.MODE_PRIVATE);
        studentsid=sharedpreferences.getString(ID,"hah");

        DatabaseReference myRef = database.getReference("Students");
        myRef.child(studentsid).child("ranking").child("digestive_system").setValue(str1);





        end = (Button) findViewById(R.id.endquiz);

        end.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Quiz.this, Displayscore.class);
                intent.putExtra("MY_KEY", str1);
                DatabaseReference reference = database.getReference("Students").child(studentsid).child("marks").child(topic);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                             totaltest=Integer.parseInt(dataSnapshot.child("tot_test").getValue().toString());
                            totaltest=totaltest+1;
                        }
                        else
                            totaltest=1;
                        reference.child("tot_test").setValue(totaltest);
                        reference.child(totaltest+"").setValue(str1);



                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                startActivity(intent);

            }
        });

    }



}

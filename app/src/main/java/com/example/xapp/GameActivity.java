package com.example.xapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    String flag="1";

    //body part images
    private ImageView[] bodyParts;
    //number of body parts
    private int numParts=6;
    //current part - will increment when wrong answers are chosen
    private int currPart;
    //number of characters in current word
    private int numChars;
    //number correctly guessed
    private int numCorr;

    public ArrayList<String> words;
    private Random rand;
    private String currWord;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    ArrayList<Classes> classes;

    private GridView letters;
    private LetterAdapter ltrAdapt;
    String topic,f="0";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle extras= getIntent().getExtras();
        if(extras!=null)
        {

           flag=extras.getString("Flag");
            Log.i("asdfgh",flag+""); }
            if(flag!=null)
            {
                topic="DIGESTIVE SYSTEM";
            }
            else
            {
                topic="ELEMENTS";
            }

      //  DatabaseReference databaseReference;
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("CLASS");
       /* databaseReference.addValueEventListener(new ValueEventListener() {
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
                                    if(ds2.getValue().toString().equals("Under Maintanence"))
                                    {
                                        t.status2="Under Maintanence";
                                    }
                                    else
                                    {
                                        t.names=new ArrayList<Classes.Subjects.Topics.Game>();
                                        words=new ArrayList<String>();
                                        Log.i("ds2",ds2+"");
                                        for (DataSnapshot ds3:ds2.getChildren()){
                                            Classes.Subjects.Topics.Game g =new Classes.Subjects.Topics.Game();
                                            g.game=ds3.getValue().toString();

                                            t.names.add(g);
                                            words.add(g.game);
                                            Log.i("kunjee",words+"");
                                        }
                                        playGame();
                                    }
                                }
                            }
                            // cls.subs.add(sub);
                        }

                    }
                    classes.add(cls);

                }



            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });*/
        /*Resources res = getResources();
        words = res.getStringArray(R.array.words);*/

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("CLASS").child("5").child("SCIENCE").child(topic).child("Game");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                words=new ArrayList<String>();
                  for(DataSnapshot ds:dataSnapshot.getChildren())
                  {
                      words.add(ds.getValue().toString());
                  }
                  playGame();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        rand = new Random();
        currWord = "";
        wordLayout = (LinearLayout)findViewById(R.id.word);

        letters = (GridView)findViewById(R.id.letters);


        bodyParts = new ImageView[numParts];
        bodyParts[0] = (ImageView)findViewById(R.id.head);
        bodyParts[1] = (ImageView)findViewById(R.id.body);
        bodyParts[2] = (ImageView)findViewById(R.id.arm1);
        bodyParts[3] = (ImageView)findViewById(R.id.arm2);
        bodyParts[4] = (ImageView)findViewById(R.id.leg1);
        bodyParts[5] = (ImageView)findViewById(R.id.leg2);

        /*playGame();*/

    }
    private void playGame() {
        String newWord = words.get(rand.nextInt(words.size()));
        while(newWord.equals(currWord)) newWord = words.get(rand.nextInt(words.size()));
        currWord = newWord;
        //play a new game



        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();


        for (int c = 0; c < currWord.length(); c++) {
            charViews[c] = new TextView(this);
            charViews[c].setText(""+currWord.charAt(c));

            charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.WHITE);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);
            //add to layout
            wordLayout.addView(charViews[c]);
        }


        ltrAdapt=new LetterAdapter(this);
        letters.setAdapter(ltrAdapt);

        currPart=0;
        numChars=currWord.length();
        numCorr=0;

        for(int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
        }
    }

    public void letterPressed(View view) {
        //user has pressed a letter to guess
        String ltr=((TextView)view).getText().toString();

        char letterChar = ltr.charAt(0);
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);

        boolean correct = false;
        for(int k = 0; k < currWord.length(); k++) {
            if(currWord.charAt(k)==letterChar){
                correct = true;
                numCorr++;
                charViews[k].setTextColor(Color.BLACK);
            }
        }

        if (correct) {
            //correct guess
            if (numCorr == numChars) {
                //user has won
                // Disable Buttons
                disableBtns();

                // Display Alert Dialog
                AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
                winBuild.setTitle("YAY");
                winBuild.setMessage("You win!\n\nThe answer was:\n\n"+currWord);
                winBuild.setPositiveButton("Play Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                GameActivity.this.playGame();
                            }});

                winBuild.setNegativeButton("Exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                GameActivity.this.finish();
                            }});

                winBuild.show();
            }
        } else if (currPart < numParts) {
            //some guesses left
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        }
        else{
            //user has lost
            disableBtns();

            // Display Alert Dialog
            AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
            loseBuild.setTitle("OOPS");
            loseBuild.setMessage("You lose!\n\nThe answer was:\n\n"+currWord);
            loseBuild.setPositiveButton("Play Again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            GameActivity.this.playGame();
                        }});

            loseBuild.setNegativeButton("Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            GameActivity.this.finish();
                        }});

            loseBuild.show();
        }


    }
    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            letters.getChildAt(l).setEnabled(false);
        }
    }


}


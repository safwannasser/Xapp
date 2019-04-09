package com.example.xapp;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class Quiz extends Activity {
    int score=0,flag=0;
    RadioGroup r1, r2, r3, r4, r5;
    Button button1, button2, button3, button4, button5, end;
    RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12, rb13, rb14, rb15,wrong;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        addListenerOnButton();

        button1 = (Button) findViewById(R.id.button1);
        r1 = (RadioGroup) findViewById(R.id.fans);


        button2= (Button) findViewById(R.id.button2);
        r2 = (RadioGroup) findViewById(R.id.sans);

        button3 = (Button) findViewById(R.id.button3);
        r3 = (RadioGroup) findViewById(R.id.tans);

        button4= (Button) findViewById(R.id.button4);
        r4 = (RadioGroup) findViewById(R.id.fourans);

        button5 = (Button) findViewById(R.id.button5);
        r5 = (RadioGroup) findViewById(R.id.fifans);



        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag=1;
                if(r1.getCheckedRadioButtonId()== -1){
                    Toast.makeText(Quiz.this, "CHOOSE A VALID OPTION" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (R.id.b2==(r1.getCheckedRadioButtonId()))
                    {
                        score=score+1;
                        rb2 = (RadioButton) findViewById(R.id.b2);
                        rb2.setTextColor(Color.GREEN);
                        rb1 = (RadioButton) findViewById(R.id.b1);
                        rb1.setTextColor(Color.BLACK);
                        rb3 = (RadioButton) findViewById(R.id.b3);
                        rb3.setTextColor(Color.BLACK);

                        // Toast.makeText(MainActivity.this, "RIGHT ANSWER!!" , Toast.LENGTH_LONG).show();
                    }
                    else{int selectedId = r1.getCheckedRadioButtonId();

                        wrong = (RadioButton) findViewById(R.id.b1);
                        wrong.setTextColor(Color.RED);
                        rb2 = (RadioButton) findViewById(R.id.b2);
                        rb2.setTextColor(Color.GREEN);

                        if(selectedId== R.id.b1)
                        {
                            rb3 = (RadioButton) findViewById(R.id.b3);
                            rb3.setTextColor(Color.BLACK);

                        }
                        else
                        {
                            rb1 = (RadioButton) findViewById(R.id.b1);
                            rb1.setTextColor(Color.BLACK);
                        }
                        //  Toast.makeText(MainActivity.this, "OOPS! WRONG ANSWER" , Toast.LENGTH_LONG).show();
                    }
                    if(flag==1){
                        button1.setEnabled(false);
                        addListenerOnButton();
                    }
                }}
        });


        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag=2;
                if(r2.getCheckedRadioButtonId()== -1){
                    Toast.makeText(Quiz.this, "CHOOSE A VALID OPTION" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (R.id.b4==(r2.getCheckedRadioButtonId()))
                    {
                        score=score+1;
                        rb4 = (RadioButton) findViewById(R.id.b4);
                        rb4.setTextColor(Color.GREEN);
                        rb5 = (RadioButton) findViewById(R.id.b5);
                        rb5.setTextColor(Color.BLACK);
                        rb6 = (RadioButton) findViewById(R.id.b6);
                        rb6.setTextColor(Color.BLACK);
                        //  Toast.makeText(MainActivity.this, "RIGHT ANSWER!!" , Toast.LENGTH_LONG).show();
                    }
                    else {

                        int selectedId = r2.getCheckedRadioButtonId();

                        wrong = (RadioButton) findViewById(R.id.b5);
                        wrong.setTextColor(Color.RED);
                        rb4 = (RadioButton) findViewById(R.id.b4);
                        rb4.setTextColor(Color.GREEN);

                        if(selectedId== R.id.b5)
                        {
                            rb6 = (RadioButton) findViewById(R.id.b6);
                            rb6.setTextColor(Color.BLACK);

                        }
                        else
                        {
                            rb5 = (RadioButton) findViewById(R.id.b5);
                            rb5.setTextColor(Color.BLACK);
                        }



                        //Toast.makeText(MainActivity.this, "OOPS! WRONG ANSWER" , Toast.LENGTH_LONG).show();
                    }
                    if(flag==2){
                        button2.setEnabled(false);
                        addListenerOnButton();
                    }
                }}
        });


        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag=3;
                if(r3.getCheckedRadioButtonId()== -1){
                    Toast.makeText(Quiz.this, "CHOOSE A VALID OPTION" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (R.id.b9==(r3.getCheckedRadioButtonId()))
                    {
                        score=score+1;
                        rb9 = (RadioButton) findViewById(R.id.b9);
                        rb9.setTextColor(Color.GREEN);
                        rb7 = (RadioButton) findViewById(R.id.b7);
                        rb7.setTextColor(Color.BLACK);
                        rb8 = (RadioButton) findViewById(R.id.b8);
                        rb8.setTextColor(Color.BLACK);
                        //  Toast.makeText(MainActivity.this, "RIGHT ANSWER!!" , Toast.LENGTH_LONG).show();
                    }
                    else {
                        int selectedId = r3.getCheckedRadioButtonId();

                        wrong = (RadioButton) findViewById(R.id.b7);
                        wrong.setTextColor(Color.RED);
                        rb9 = (RadioButton) findViewById(R.id.b9);
                        rb9.setTextColor(Color.GREEN);

                        if(selectedId== R.id.b7)
                        {
                            rb8 = (RadioButton) findViewById(R.id.b8);
                            rb8.setTextColor(Color.BLACK);

                        }
                        else
                        {
                            rb7 = (RadioButton) findViewById(R.id.b7);
                            rb7.setTextColor(Color.BLACK);
                        }

                        //Toast.makeText(MainActivity.this, "OOPS! WRONG ANSWER" , Toast.LENGTH_LONG).show();
                    }
                    if(flag==3){
                        button3.setEnabled(false);
                        addListenerOnButton();
                    }
                }}
        });






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






        button5.setOnClickListener(new View.OnClickListener() {

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
        });




    }


    public void addListenerOnButton() {

        final Context context = this;
        final String str1 = Integer.toString(score);

        end = (Button) findViewById(R.id.endquiz);

        end.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Quiz.this, Displayscore.class);
                intent.putExtra("MY_KEY", str1);
                startActivity(intent);

            }
        });

    }



}

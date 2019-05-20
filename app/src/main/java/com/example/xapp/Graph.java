package com.example.xapp;

//package com.android_examples.barchart_android_examplescom;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Graph extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreferences = "myprefer";
    Context context;
    public static final String ID = "sid";
    BarChart chart ;
    String studentsid;
    Integer totaltest;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        sharedpreferences = getSharedPreferences(mypreferences, Context.MODE_PRIVATE);
        studentsid = sharedpreferences.getString(ID, "hah");
        Log.i("zxcv",studentsid);
        chart = (BarChart) findViewById(R.id.chart1);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        AddValuesToBARENTRY();




    }

    public void AddValuesToBARENTRY(){


        DatabaseReference ref = database.getReference("Students").child(studentsid).child("marks");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    totaltest=Integer.parseInt(dataSnapshot.child("tot_test").getValue().toString());
                    Log.i("qwerty",totaltest+"gfd");
                    for(int i=1;i<=totaltest;i++)
                    {
                     //   Integer qnum=Integer.parseInt(dataSnapshot.getKey());
                     //   Log.i("abcc",qnum+"");
                        Float qmar=Float.parseFloat(dataSnapshot.child(i+"").getValue().toString());
                        BARENTRY.add(new BarEntry(qmar , i-1));
                        BarEntryLabels.add("Test "+i);
                    }
                    Bardataset = new BarDataSet(BARENTRY, "Marks Scored");

                    BARDATA = new BarData(BarEntryLabels, Bardataset);

                    Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

                    chart.setData(BARDATA);

                    chart.animateY(3000);
                }
                else

                    Toast.makeText(getApplicationContext(), " Test not attempted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}

package com.example.xapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.util.ArrayList;

public class RankAdapter extends BaseAdapter {
    ArrayList<Classes> classes;
    ArrayList<Classes.Subjects> subjects;
    ArrayList<Classes.Subjects.Topics> topics;
    int class_sub=1,classnum,subnum;
    Context context;
    RankAdapter(ArrayList<Classes> classes, Context context,int class_sub)
    {
        class_sub=1;
        this.classes=classes;
        this.context=context;
    }
    void setSubjectData(ArrayList<Classes.Subjects> subjcts,int classnum)
    {
        class_sub=2;
        this.classnum=classnum;
        this.subjects=subjcts;
        this.context=context;
    }
    void setTopicData(ArrayList<Classes.Subjects.Topics> topics,int subnum)
    {
        class_sub=3;
        this.subnum=subnum;
        this.topics=topics;
        this.context=context;
    }

    @Override
    public int getCount() {
        if(class_sub==1)
            return classes.size();
        else if (class_sub==2)
            return classes.get(classnum).subs.size();
        else
            return classes.get(classnum).subs.get(subnum).topics.size();
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
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView=(TextView)convertView.findViewById(R.id.item);
        if(class_sub==1)
            textView.setText(classes.get(position).clss);
        else if (class_sub==2)
            textView.setText(classes.get(classnum).subs.get(position).name);
        else
            textView.setText(classes.get(classnum).subs.get(subnum).topics.get(position).topic);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(context,classes.get(position).status,Toast.LENGTH_LONG).show();
                Subject subject=(Subject)context;
                Ranking rank=(Ranking)context;
                if(class_sub==1){
                    if (classes.get(position).status.equals("xa"))
                        showToastMessage("Under Maintanence",500);
                    else
                        subject.setSubjects(position);
                }


                else if (class_sub==2){
                    if(subjects.get(position).topics==null)
                        showToastMessage("Under Maintanence",500);
                    else
                        subject.setTopics(classnum,position);}
                else if(class_sub==3)
                {
                  /* TextView sub=(TextView)v.findViewById(R.id.item);
                    String subj=sub.getText().toString();*/
                    if(position==0)

                    {
                      // ArrayList<Studentname>
                    }
                        //rank.toDigest();
                    else
                        rank.tophoto();
                    //showToastMessage("Under Maintanence",500);



                }


            }


        });

        return convertView;
    }
    public void showToastMessage(String text, int duration){
        final Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);
    }

}

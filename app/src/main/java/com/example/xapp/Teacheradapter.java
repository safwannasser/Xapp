package com.example.xapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xapp.R;

import java.util.ArrayList;

public class Teacheradapter extends BaseAdapter {
 ArrayList<Teachername> teachers;
 Context context;
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
        return convertView;
    }
}

package com.example.xapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    ArrayList<Messages> msgList;
    Context context;
    ChatAdapter(Context context,ArrayList<Messages> msgList)
    {
        this.context=context;
        this.msgList=msgList;
    }

    void getData(ArrayList<Messages> msgList)
    {
        this.msgList=msgList;
    }

    @Override
    public int getCount() {
        return msgList.size();
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
        final LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView m_text;
        if(msgList.get(position).msg_sender.equals("s"))
        {
            convertView = inflater.inflate(R.layout.message_sent,null);
            m_text=(TextView)convertView.findViewById(R.id.msg_sent);
        }
        else
        {
            convertView=inflater.inflate(R.layout.message_recieved,null);
            m_text=(TextView)convertView.findViewById(R.id.msg_recieved);
        }
        m_text.setText(msgList.get(position).msg_txt);
        return convertView;

    }
}

package com.example.xapp;

import java.sql.Statement;

public class Messages {
       public String msg_txt="";
       public  String msg_sender="";
       Messages(String msgtxt,String sender)
       {
              this.msg_sender=sender;
              this.msg_txt=msgtxt;
       }
       Messages()
       {

       }
}
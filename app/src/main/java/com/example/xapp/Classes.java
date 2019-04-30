package com.example.xapp;

import java.util.ArrayList;

public class Classes {
    String clss,status;
    ArrayList<Subjects> subs;
    static class Subjects
    {
        String name,status1;
        public ArrayList<Topics> topics;
        static class Topics
        {
            String topic;
            ArrayList<String> names;
        }
    }
}

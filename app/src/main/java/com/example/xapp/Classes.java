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
            String topic,status2;
            public ArrayList<Game> names;
            static class Game
            {
                String game;
                public ArrayList<String> gname;
            }
        }
    }
}

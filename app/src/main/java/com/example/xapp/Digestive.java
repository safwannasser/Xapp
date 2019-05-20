package com.example.xapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import net.alexandroid.utils.exoplayerhelper.ExoPlayerHelper;
import net.alexandroid.utils.exoplayerhelper.ExoPlayerListener;


public class Digestive extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Videos");
    DatabaseReference ref=database.getReference("Teacher");
    String faculty_name,key,rate;

    TextView name,cur_rate;

    public static final String SAMPLE_1 = "https://firebasestorage.googleapis.com/v0/b/xapp-b979f.appspot.com/o/storage%2Femulated%2F0%2FWhatsApp%2FMedia%2FWhatsApp%20Video%2FVID-20190314-WA0001.mp4?alt=media&token=077e742e-e466-4a2e-a4d6-57f3dd82dc27";
    public static final String TEST_TAG_URL = "https://firebasestorage.googleapis.com/v0/b/xapp-b979f.appspot.com/o/storage%2Femulated%2F0%2FWhatsApp%2FMedia%2FWhatsApp%20Video%2FVID-20190314-WA0001.mp4?alt=media&token=077e742e-e466-4a2e-a4d6-57f3dd82dc27";
    private ExoPlayerHelper mExoPlayerHelper;
    ProgressDialog pd;
    Button rating,quiz,game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digestive);
        SimpleExoPlayerView exoPlayerView = findViewById(R.id.exoPlayerView);
        pd = new ProgressDialog(Digestive.this);
        pd.setMessage("Buffering video please wait...");
        pd.show();

        mExoPlayerHelper = new ExoPlayerHelper.Builder(this, exoPlayerView)
                .addMuteButton(false, false)
                .setUiControllersVisibility(true)
                .setRepeatModeOn(false)
                .setAutoPlayOn(true)
                .setVideoUrls(SAMPLE_1)
                .setTagUrl(TEST_TAG_URL)


                .addSavedInstanceState(savedInstanceState)

                .enableLiveStreamSupport()
                .addProgressBarWithColor(getResources().getColor(R.color.colorAccent))
                .setFullScreenBtnVisible()
                .setMuteBtnVisible()
                .createAndPrepare()
                ;

        mExoPlayerHelper.setExoPlayerEventsListener(new ExoPlayerListener() {
            @Override
            public void onLoadingStatusChanged(boolean isLoading, long bufferedPosition, int bufferedPercentage) {

            }

            @Override
            public void onPlayerPlaying(int currentWindowIndex) {
                pd.dismiss();
            }

            @Override
            public void onPlayerPaused(int currentWindowIndex) {

            }

            @Override
            public void onPlayerBuffering(int currentWindowIndex) {

            }

            @Override
            public void onPlayerStateEnded(int currentWindowIndex) {

            }

            @Override
            public void onPlayerStateIdle(int currentWindowIndex) {

            }

            @Override
            public void onPlayerError(String errorString) {

            }

            @Override
            public void createExoPlayerCalled(boolean isToPrepare) {

            }

            @Override
            public void releaseExoPlayerCalled() {

            }

            @Override
            public void onVideoResumeDataLoaded(int window, long position, boolean isResumeWhenReady) {

            }

            @Override
            public void onTracksChanged(int currentWindowIndex, int nextWindowIndex, boolean isPlayBackStateReady) {

            }

            @Override
            public void onMuteStateChanged(boolean isMuted) {

            }

            @Override
            public void onVideoTapped() {

            }

            @Override
            public boolean onPlayBtnTap() {
                return false;
            }

            @Override
            public boolean onPauseBtnTap() {
                return false;
            }

            @Override
            public void onFullScreenBtnTap() {

            }
        });
      //  onBackpressed();
        addListenerOnButton();
        addListenerOnQuizButton();
        addListenerOnGameButton();

        name=(TextView)findViewById(R.id.teachername);
        cur_rate=(TextView)findViewById(R.id.rating);
        myRef.child("DIGESTIVE SYSTEM").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                faculty_name=dataSnapshot.getValue().toString();
                Log.d("Teacher Name",faculty_name);
                name.setText(faculty_name);
                final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Teacher").orderByChild("name");
                userQuery.equalTo(faculty_name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //  Log.e("inside query",dataSnapshot.toString());
                        for(DataSnapshot teachersnapshot : dataSnapshot.getChildren())
                        {
                            key= teachersnapshot.getKey();
                            Log.i("uid",key);

                            ref.child(key).child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //rate=dataSnapshot.getValue().toString();
                                    for(DataSnapshot ds:dataSnapshot.getChildren())
                                    {
                                        rate=(ds.getValue().toString());
                                        int number_ppl=Integer.parseInt(ds.getKey());
                                        Log.i("rare",rate);
                                        cur_rate.setText(rate);
                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onBackPressed() {
        if (mExoPlayerHelper != null) {
            mExoPlayerHelper.playerPause();
          //  mExoPlayerHelper.;
          //  mExoPlayerHelper.seekTo(0);
            super.onBackPressed();
            
        }
        }

        //super.onBackPressed();

    public static class ListAdapter {
    }

    public void addListenerOnButton()
    {
        rating = (Button)findViewById(R.id.ratebutton);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mExoPlayerHelper != null) {
                    mExoPlayerHelper.playerPause();
                    //  mExoPlayerHelper.;
                    //  mExoPlayerHelper.seekTo(0);


                }

                Intent intent = new Intent(Digestive.this, Rating.class);
                intent.putExtra("MY_KEY", key);
                startActivity(intent);
            }
        });
    }
    public void addListenerOnQuizButton()
    { String flag="0";
        quiz = (Button)findViewById(R.id.quizbutton);
        quiz.setOnClickListener(v -> {
            if (mExoPlayerHelper != null) {
                mExoPlayerHelper.playerPause();
                //  mExoPlayerHelper.;
                //  mExoPlayerHelper.seekTo(0);


            }

            Intent intent = new Intent(Digestive.this, Quiz.class);
            intent.putExtra("Flags",flag);
            startActivity(intent);
        });
    }

    public void addListenerOnGameButton()
    {   String flag="0";
        game = (Button)findViewById(R.id.gamebutton);
        game.setOnClickListener(v -> {
            if (mExoPlayerHelper != null) {
                mExoPlayerHelper.playerPause();
                //  mExoPlayerHelper.;
                //  mExoPlayerHelper.seekTo(0);


            }

            Intent intent = new Intent(Digestive.this, Hangman.class);
            intent.putExtra("Flag",flag);
            startActivity(intent);
        });
    }


}

package com.example.xapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import net.alexandroid.utils.exoplayerhelper.ExoPlayerHelper;
import net.alexandroid.utils.exoplayerhelper.ExoPlayerListener;


public class Digestive extends AppCompatActivity {

    public static final String SAMPLE_1 = "https://firebasestorage.googleapis.com/v0/b/xapp-b979f.appspot.com/o/storage%2Femulated%2F0%2FWhatsApp%2FMedia%2FWhatsApp%20Video%2FVID-20190314-WA0001.mp4?alt=media&token=077e742e-e466-4a2e-a4d6-57f3dd82dc27";
    public static final String TEST_TAG_URL = "https://firebasestorage.googleapis.com/v0/b/xapp-b979f.appspot.com/o/storage%2Femulated%2F0%2FWhatsApp%2FMedia%2FWhatsApp%20Video%2FVID-20190314-WA0001.mp4?alt=media&token=077e742e-e466-4a2e-a4d6-57f3dd82dc27";
    private ExoPlayerHelper mExoPlayerHelper;
    ProgressDialog pd;

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

}

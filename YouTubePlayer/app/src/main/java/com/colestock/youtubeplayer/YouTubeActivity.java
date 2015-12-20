package com.colestock.youtubeplayer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private String GOOGLE_API_KEY = "AIzaSyBBkTBnO_PdzoNzJ2GN5qF4JGzBIZemjO0";
    private String YOUTUBE_VIDEO_ID = "EFLaUL8NG9Y";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        YouTubePlayerView youTubePlayer = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayer.initialize(GOOGLE_API_KEY, this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Initialized YouTube Player Successfully", Toast.LENGTH_SHORT);
        Log.d("PlaybackEventListener", "youtube player initialized successfully");
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if(!wasRestored) {
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onPlaying() {
            Log.d("PlaybackEventListener", "playing!");
            Toast.makeText(YouTubeActivity.this,"playing!",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Log.d("PlaybackEventListener", "paused!");
            Toast.makeText(YouTubeActivity.this,"paused!",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {
            Log.d("PlaybackEventListener", "stopped!");
            Toast.makeText(YouTubeActivity.this,"stopped!",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YouTubeActivity.this,"not another ad!",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YouTubeActivity.this,"video just started",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d("YouTubeActivity", youTubeInitializationResult.toString());
        Toast.makeText(this,"Failed to initialize YouTubePlayer",Toast.LENGTH_SHORT);
    }
}

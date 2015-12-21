package com.colestock.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {

    private String GOOGLE_API_KEY = "AIzaSyBBkTBnO_PdzoNzJ2GN5qF4JGzBIZemjO0";
    private String YOUTUBE_VIDEO_ID = "EFLaUL8NG9Y";
    private String PLAYLIST_ID = "PLC786485413C61FB6";
    private Button btnPlayVideo;
    private Button btnPlayPlaylist;

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.btnPlayVideo:
                break;
            case R.id.btnPlayPlaylist:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnPlayVideo = (Button) findViewById(R.id.btnPlayVideo);
        btnPlayVideo.setOnClickListener(this);
        btnPlayPlaylist = (Button) findViewById(R.id.btnPlayPlaylist);
        btnPlayPlaylist.setOnClickListener(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}

package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;
import android.widget.MediaController;
import android.net.Uri;

public class VideoActivity extends AppCompatActivity {

    VideoView vid;
    MediaController m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vid = (VideoView)findViewById(R.id.videoView);
        m = new MediaController(this);

        String path = "android.resource://com.GitHub.TALK/"+R.raw.cats;

        Uri u = Uri.parse(path);
        vid.setVideoURI(u);
        vid.start();
    }
}
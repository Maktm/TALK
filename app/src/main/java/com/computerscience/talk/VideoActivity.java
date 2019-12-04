package com.computerscience.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.View;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;
import android.widget.MediaController;
import android.net.Uri;
import android.graphics.PixelFormat;

public class VideoActivity extends AppCompatActivity {

    VideoView vid;
    MediaController m;
    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    private SurfaceHolder getWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getWindow.setFormat(PixelFormat.UNKNOWN);

        vid = (VideoView)findViewById(R.id.videoView);
        m = new MediaController(this);

        String path = "raw/cats.mp4";

        Uri u = Uri.parse(path);
        vid.setVideoURI(u);
        vid.requestFocus();
        vid.start();
    }
}
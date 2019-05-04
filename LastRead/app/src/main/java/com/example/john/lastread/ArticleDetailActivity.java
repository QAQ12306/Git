package com.example.john.lastread;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ArticleDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton play,pause,stop,volume_plus,volume_decrease;
    private TextView musicName,musicLength,musicCur;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private AudioManager audioManager;
    private Timer timer;
    int maxVolume,currentVolume;
    private boolean isSeekBarChanging;//互斥变量，防止进度条与定时器冲突。
     private int currentPosition;//当前音乐播放的进度
    SimpleDateFormat format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.article_detail_item);
     audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
     format = new SimpleDateFormat("mm:ss");
     play = (ImageButton) findViewById(R.id.play);
     pause = (ImageButton) findViewById(R.id.pause);
     stop = (ImageButton) findViewById(R.id.stop);

     musicName = (TextView) findViewById(R.id.music_name);
     musicLength = (TextView) findViewById(R.id.music_length);
     musicCur = (TextView) findViewById(R.id.music_cur);

     seekBar = (SeekBar) findViewById(R.id.seekBar);
     seekBar.setOnSeekBarChangeListener(new MySeekBar());

     play.setOnClickListener(ArticleDetailActivity.this);
     pause.setOnClickListener(ArticleDetailActivity.this);
     stop.setOnClickListener(ArticleDetailActivity.this);

        currentPosition = mediaPlayer.getCurrentPosition();

        if (ContextCompat.checkSelfPermission(ArticleDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
         ActivityCompat.requestPermissions(ArticleDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
     }else {
         initMediaPlayer();//初始化mediaplayer
          }
    }
    private void initMediaPlayer() {
        try {
            Uri setDataSourceuri = Uri.parse("android.resource://com.example.john.lastread/"+R.raw.ti);
            //指定音频文件的路径
            mediaPlayer.setDataSource(this,setDataSourceuri);
             mediaPlayer.prepare();//让mediaplayer进入准备状态
             mediaPlayer.setLooping(true);
             mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                 public void onPrepared(MediaPlayer mp) {
                     seekBar.setMax(mediaPlayer.getDuration());
                     musicLength.setText(format.format(mediaPlayer.getDuration())+""); musicCur.setText("00:00");
                     musicName.setText("music.mp3");
                 } });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                //Toast.makeText(ArticleDetailActivity.this,"开始播放",Toast.LENGTH_SHORT).show();
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();//开始播放
                    //mediaPlayer.seekTo(currentPosition); //监听播放时回调函数
                    if(!isSeekBarChanging){
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                     timer = new Timer();
                     timer.schedule(new TimerTask() {
                         Runnable updateUI = new Runnable() {
                             @Override
                             public void run() {
                                 musicCur.setText(format.format(mediaPlayer.getCurrentPosition())+"");
                             }
                         };
                         @Override
                         public void run() {
                             if(!isSeekBarChanging){
                                 seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                 runOnUiThread(updateUI);
                             }
                         }
                         },0,50);
                }
                break;
                case R.id.pause:
                    //Toast.makeText(ArticleDetailActivity.this,"暂停播放",Toast.LENGTH_SHORT).show();
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();//暂停播放
                         }
                    break;
                    case R.id.stop:
                        Toast.makeText(ArticleDetailActivity.this,"停止播放",Toast.LENGTH_SHORT).show();
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.reset();//停止播放
                             initMediaPlayer();
                        }
                        break;
                default:
                    break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isSeekBarChanging = true;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        } if (timer != null){
            timer.cancel();
            timer = null;
        }
    }
    /*进度条处理*/
    public class MySeekBar implements SeekBar.OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }
        /*滚动时,应当暂停后台定时器*/
        public void onStartTrackingTouch(SeekBar seekBar) { isSeekBarChanging = true; }
        /*滑动结束后，重新设置值*/
        public void onStopTrackingTouch(SeekBar seekBar) { isSeekBarChanging = false; mediaPlayer.seekTo(seekBar.getProgress());
        }
                    }
}





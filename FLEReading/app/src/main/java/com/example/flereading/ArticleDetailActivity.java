package com.example.flereading;

import android.Manifest;
import android.app.Service;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lauzy.freedom.library.Lrc;
import com.lauzy.freedom.library.LrcHelper;
import com.lauzy.freedom.library.LrcView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;

public class ArticleDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private LrcView mLrcView;
    private ImageButton play,pause,stop;
    private TextView musicName,musicLength,musicCur;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Handler mHandler = new Handler();
    private AudioManager audioManager;
    private Timer timer;
    private boolean isSeekBarChanging;//互斥变量，防止进度条与定时器冲突。
    private int currentPosition;//当前音乐播放的进度
    SimpleDateFormat format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail_item);
        audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
        format = new SimpleDateFormat("mm:ss");
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);

        //musicName = (TextView) findViewById(R.id.music_name);
        musicLength = findViewById(R.id.music_length);
        musicCur = findViewById(R.id.music_cur);


        seekBar = findViewById(R.id.seekBar);
        /*        seekBar.setOnSeekBarChangeListener(new MySeekBar());*/

        play.setOnClickListener(ArticleDetailActivity.this);
        pause.setOnClickListener(ArticleDetailActivity.this);
        stop.setOnClickListener(ArticleDetailActivity.this);

        Toolbar toolbar1=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);



        currentPosition = mediaPlayer.getCurrentPosition();

        if (ContextCompat.checkSelfPermission(ArticleDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ArticleDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            initMediaPlayer();//初始化mediaplayer
        }
        init();

    }

    private void initMediaPlayer() {
        try {
            Uri setDataSourceuri = Uri.parse("android.resource://com.example.flereading/"+R.raw.tm);
            //指定音频文件的路径
            mediaPlayer.setDataSource(this,setDataSourceuri);
            mediaPlayer.prepare();//让mediaplayer进入准备状态
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    mHandler.removeCallbacks(mRunnable);
                    seekBar.setMax(mediaPlayer.getDuration());
                    musicLength.setText(format.format(mediaPlayer.getDuration())+""); musicCur.setText("00:00");
                } });
            mediaPlayer.prepareAsync();
            mHandler.post(mRunnable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mediaPlayer.getCurrentPosition();
            mLrcView.updateTime(currentPosition);
            seekBar.setProgress(currentPosition);
            musicCur.setText(LrcHelper.formatTime(currentPosition));
            mHandler.postDelayed(this, 100);
        }
    };

    private void init() {
        List<Lrc> lrcs = LrcHelper.parseLrcFromAssets(this, "tm.lrc");
        mLrcView = findViewById(R.id.lrc_view);

        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        mLrcView.setLrcData(lrcs);
        mLrcView.setOnPlayIndicatorLineListener(new LrcView.OnPlayIndicatorLineListener() {
            @Override
            public
            void onPlay(long time, String content) {
                mediaPlayer.seekTo((int) time);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public
            void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mHandler.removeCallbacks(mRunnable);
                }
                Log.d("progress:", " "+progress);
            }

            @Override
            public
            void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mHandler.post(mRunnable);
                mediaPlayer.seekTo(seekBar.getProgress());

            }
        });
    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();//开始播放
                    mLrcView.resume();
                    mHandler.post(mRunnable);
                    Toast.makeText(ArticleDetailActivity.this,"开始播放",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pause:

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();//暂停播放
                    mLrcView.pause();
                    Toast.makeText(ArticleDetailActivity.this,"暂停播放",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.stop:

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();//停止播放
                    Toast.makeText(ArticleDetailActivity.this,"停止播放",Toast.LENGTH_SHORT).show();
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }

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
}

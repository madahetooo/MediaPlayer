package eslam.com.newmediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayer extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    ImageView ivPlay, ivPause, ivStop;
    SeekBar mSeekBar;
    MediaPlayer mMediaPlayer;
    int seekPostion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ivPause = findViewById(R.id.iv_Pause);
        ivPlay = findViewById(R.id.iv_Play);
        ivStop = findViewById(R.id.iv_Stop);
        mSeekBar = findViewById(R.id.mSeekBar);

        ivStop.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivPause.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (mMediaPlayer != null)
                    mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
            }
        }, 0, 500);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_Play:
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.enta_t2dr);
                    mSeekBar.setMax(mMediaPlayer.getDuration());
                    mMediaPlayer.start();
                } else if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.seekTo(seekPostion);
                    mMediaPlayer.start();
                }
                break;
            case R.id.iv_Pause:
                mMediaPlayer.pause();
                seekPostion = mMediaPlayer.getCurrentPosition();
                break;
            case R.id.iv_Stop:
                if (mMediaPlayer != null) {
                    mSeekBar.setProgress(0);
                    mMediaPlayer.stop();
                    mMediaPlayer = null;
                }
                break;

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser)
            mMediaPlayer.seekTo(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

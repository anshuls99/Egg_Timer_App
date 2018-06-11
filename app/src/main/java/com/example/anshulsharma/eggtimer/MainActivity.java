package com.example.anshulsharma.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import javax.xml.validation.TypeInfoProvider;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mplayer;
    TextView timerText;
    SeekBar timer;
    Boolean timerActive=false;
    Button start;
    CountDownTimer countDownTimer;

    public void reset(){

        start.setText("GO");
        timer.setVisibility(View.VISIBLE);
        timerText.setText("00:30");
        timer.setProgress(30);
        countDownTimer.cancel();
        timerActive=false;

    }


    public void countDown(View view){
        if(timerActive==false) {
            timerActive=true;
            int pro = timer.getProgress();
            final int min = (pro * 1000) / 60000;
            final int sec = (pro * 1000) % 60000;
            timer.setVisibility(View.INVISIBLE);
            start.setText("STOP");
            countDownTimer=new CountDownTimer(pro * 1000, 1000) {
                int a = min, b = sec / 1000;

                public void onTick(long millisUntilFinished) {
                    if (b == 0) {
                        a--;
                        b = 59;
                    } else
                        b--;
                    timerText.setText(Integer.toString(a) + ":" + Integer.toString(b));
                    //here you can have your logic to set text to edittext
                }

                public void onFinish() {

                    reset();
                    mplayer.start();
                }

            }.start();

        }else{

         reset();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mplayer=MediaPlayer.create(this,R.raw.air);
        start = findViewById(R.id.timerButton);
        timer=findViewById(R.id.seekBar);
        timerText=findViewById(R.id.timer);
        timer.setMax(600);
        timer.setProgress(30);
        timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min=progress/60;
                int sec=progress%60;
                if(sec<10)
                timerText.setText(Integer.toString(min)+":0"+Integer.toString(sec));
                else if(min==10)
                    timerText.setText(Integer.toString(min)+":"+Integer.toString(sec)+"0");
                else
                    timerText.setText(Integer.toString(min)+":"+Integer.toString(sec));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






    }
}

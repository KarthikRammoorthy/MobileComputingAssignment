package com.example.karthikrammoorthy.timerapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class TimerActivity extends AppCompatActivity {

    private Button btnReset;
    private Button btnPause;
    private TextView txtTimer;
    private CountDownTimer timer;
    private long millisInFuture, remainingMillis, minutes, seconds = 0;
    private boolean paused, stopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnReset = findViewById(R.id.btnReset);
        btnPause = findViewById(R.id.btnPause);
        txtTimer = findViewById(R.id.txtTimer);

        Intent intent = getIntent();

        minutes = Integer.parseInt(intent.getStringExtra(MainActivity.MESSAGE_MINUTES));
        seconds = Integer.parseInt(intent.getStringExtra(MainActivity.MESSAGE_SECONDS));

        millisInFuture = minutes*60000 + seconds*1000;

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                paused = false;
                setTxtTimerValue(millisInFuture);

                if(!stopped){
                    timer.cancel();
                    btnReset.setText(R.string.start);
                    btnPause.setText(R.string.pause);
                    btnPause.setEnabled(false);
                    stopped = true;
                } else {

                    startCountDownTimer();
                    btnReset.setText(R.string.reset);
                    btnPause.setEnabled(true);
                    stopped = false;
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!paused) {
                    timer.cancel();
                    btnPause.setText(R.string.resume);
                    paused = true;

                } else {
                    startCountDownTimer();
                    btnPause.setText(R.string.pause);
                    paused = false;
                }
            }
        });



    }

    private void  startCountDownTimer() {

        long startFrom = this.paused ? remainingMillis : millisInFuture;
        timer = new CountDownTimer(startFrom, 1000 ) {
            @Override
            public void onTick(long millisLeft) {
                remainingMillis = millisLeft;
                setTxtTimerValue(millisLeft);


            }

            @Override
            public void onFinish() {

                setTxtTimerValue(0);
                Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();


            }
        }.start();

    }

    public void setTxtTimerValue(long millis){

        minutes = millis/60000;
        seconds = (millis / 1000) % 60;

        String minutesVal = String.valueOf(minutes);
        String secondsVal = String.valueOf(seconds);

        secondsVal = secondsVal.replaceAll("(?<!\\d)\\d(?!\\d)", "0$0");
        txtTimer.setText(getString(R.string.txtTimer,minutesVal,secondsVal));;



    }

    @Override
    protected  void onStop(){
        super.onStop();

        timer.cancel();
        paused = true;

        Toast.makeText(this, "Stop called!", Toast.LENGTH_SHORT).show();



    }

    @Override
    protected  void  onStart() {
        super.onStart();
        if(!stopped) {
            startCountDownTimer();
            btnReset.setText(R.string.reset);
            btnPause.setText(R.string.pause);
            paused = false;
        }
        Toast.makeText(this,"Start called!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Destroy called!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected  void  onPause() {
        super.onPause();
        Toast.makeText(this, "Pause called!", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resume called!", Toast.LENGTH_SHORT).show();
    }

}

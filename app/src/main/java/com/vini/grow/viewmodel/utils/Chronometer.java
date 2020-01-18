package com.vini.grow.viewmodel.utils;

import android.os.CountDownTimer;
import android.os.Handler;

public class Chronometer {
    private boolean isRunning;
    private Handler countUpHandler = new Handler();
    private CountDownTimer countDownTimer;
    private long startTime;

    private Runnable countUpRunnable = new Runnable() {
        @Override
        public void run() { // / Updates timer label every second
            long millis = System.currentTimeMillis() - startTime;
            //updateTimerText(timerText, millis);

            countUpHandler.postDelayed(countUpRunnable, 1000);
        }
    };

    public Chronometer(){
        startTime = 0;
        isRunning = false;

        // Initialization is necessary in order to cancel without crashing
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {}
            @Override
            public void onFinish() {}
        };
    }

    public void startCountUp(){

    }

    public void startCountDown(int minutes){

    }

    public boolean isRunning(){
        return true;
    }

    public void stopChronometer(){

    }

}

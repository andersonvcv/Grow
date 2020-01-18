package com.vini.grow.viewmodel.utils;

import android.os.CountDownTimer;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

public class Chronometer {
    private long startTime;
    private MutableLiveData<String> timerText;
    private MutableLiveData<Boolean> isTimerRunning;
    private String STRINGFORMAT;

    private Presenter presenter;

    private CountDownTimer countDownTimer;

    private Handler countUpHandler = new Handler();
    private Runnable countUpRunnable = new Runnable() {
        @Override
        public void run() { // Runs this method periodically
            long millis = System.currentTimeMillis() - startTime;
//            updateTimerText(timerText, millis);
            presenter.updateTimerText(timerText, millis, STRINGFORMAT);
            countUpHandler.postDelayed(countUpRunnable, 1000);
        }
    };

    public Chronometer(MutableLiveData<String> timerText, String STRINGFORMAT, MutableLiveData<Boolean> isTimerRunning){
        startTime = 0;
        presenter = new Presenter();

        this.STRINGFORMAT = STRINGFORMAT;
        this.timerText = timerText;
        this.isTimerRunning = isTimerRunning;
        isTimerRunning.setValue(false);

        // Initialization is necessary in order to cancel without crashing
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {}
            @Override
            public void onFinish() {}
        };
    }

    public void startCountUp(){
        isTimerRunning.setValue(true);
        startTime = System.currentTimeMillis();
//        updateTimerText(timerText,0);
        presenter.updateTimerText(timerText, 0, STRINGFORMAT);

        countUpHandler.removeCallbacks(countUpRunnable);
        countUpHandler.postDelayed(countUpRunnable, 1000); // Updates timer label every second

    }

    public void startCountDown(int minutes){
        isTimerRunning.setValue(true);
//        updateTimerText(timerText, minutes);
        presenter.updateTimerText(timerText, minutes, STRINGFORMAT);
        countDownTimer = new CountDownTimer(minutes * 1000 * 60, 1000){
            @Override
            public void onTick(long millisUntillFinished) {
//                updateTimerText(timerText, millisUntillFinished);
                presenter.updateTimerText(timerText, millisUntillFinished, STRINGFORMAT);
            }

            @Override
            public void onFinish() {
                isTimerRunning.setValue(false);
            }
        }.start();
    }

    public boolean isRunning(){
        return isTimerRunning.getValue();
    }

    public void stop(){
        // Stop Handler/CountDownTimer
        countUpHandler.removeCallbacks(countUpRunnable);
        countDownTimer.cancel();
        isTimerRunning.setValue(false);
    }

//    // Update View livedata through data binding
//    private void updateTimerText(MutableLiveData mutableLiveData, int min){
//        int minutes = min;
//        int hours = minutes / 60;
//        minutes = minutes % 60;
//
//        mutableLiveData.setValue(String.format(STRINGFORMAT, hours, minutes, 0));
//    }
//    private void updateTimerText(MutableLiveData mutableLiveData, long miliseconds){
//        int seconds = (int) (miliseconds / 1000);
//        int minutes = seconds / 60;
//        int hours = minutes / 60;
//        seconds = seconds % 60;
//        minutes = minutes % 60;
//        mutableLiveData.setValue(String.format(STRINGFORMAT, hours, minutes, seconds));
//    }
}

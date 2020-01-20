package com.vini.grow.viewmodel.utils;

import android.os.CountDownTimer;
import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

// Android studio have a chronometer class but it restricts the minimun api to 23
public class Chronometer {
    private long startTime;
    private MutableLiveData<String> timerText;
    private MutableLiveData<Boolean> isTimerRunning;
    private String STRINGFORMAT;

    private IChronometerPresenter presenter;

    private CountDownTimer countDownTimer;

    private Handler countUpHandler = new Handler();
    private Runnable countUpRunnable = new Runnable() {
        @Override
        public void run() { // Runs this method periodically
            long millis = System.currentTimeMillis() - startTime;
            presenter.updateTimerText(timerText, millis, STRINGFORMAT);
            countUpHandler.postDelayed(countUpRunnable, 1000);
        }
    };

    public Chronometer(MutableLiveData<String> timerText, IChronometerPresenter presenter, String STRINGFORMAT, MutableLiveData<Boolean> isTimerRunning){
        startTime = 0;
        this.presenter = presenter;

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
        presenter.updateTimerText(timerText, 0, STRINGFORMAT);

        countUpHandler.removeCallbacks(countUpRunnable);
        countUpHandler.postDelayed(countUpRunnable, 1000); // Updates timer label every second

    }

    public void startCountDown(int minutes){
        isTimerRunning.setValue(true);
        presenter.updateTimerText(timerText, minutes, STRINGFORMAT);
        countDownTimer = new CountDownTimer(minutes * 1000 * 60, 1000){
            @Override
            public void onTick(long millisUntillFinished) {
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
}

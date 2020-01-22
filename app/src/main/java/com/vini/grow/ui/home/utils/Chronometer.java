package com.vini.grow.ui.home.utils;

import android.os.CountDownTimer;
import android.os.Handler;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Chronometer {
    private MutableLiveData<String> timerText;
    private MutableLiveData<Boolean> isTimerRunning;
    private MutableLiveData<String> countDownText;
    private int iCountDown;
    private long startTime;
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

    public Chronometer(MutableLiveData<String> timerText, IChronometerPresenter presenter, String STRINGFORMAT){
        countDownText = new MutableLiveData<>();
        isTimerRunning = new MutableLiveData<>();

        this.presenter = presenter;
        this.timerText = timerText;
        this.STRINGFORMAT = STRINGFORMAT;

        startTime = 0;
        iCountDown = 10;
        countDownText.setValue("Cancel (" + iCountDown + ")");
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
                if (iCountDown >= 0){
                    iCountDown = iCountDown-1;
                    countDownText.setValue("Cancel (" + iCountDown + ")");
                }
                else if (iCountDown >= -1){
                    iCountDown = iCountDown-1;
                    countDownText.setValue("Cancel");
                }
            }

            @Override
            public void onFinish() {
                isTimerRunning.setValue(false);
            }
        }.start();
    }

    public void stop(){
        // Stop Handler/CountDownTimer
        countUpHandler.removeCallbacks(countUpRunnable);
        countDownTimer.cancel();
        isTimerRunning.setValue(false);
        iCountDown = 10;
    }

    public LiveData<Boolean> getIsTimerRunning(){return isTimerRunning;}
    public LiveData<String> getCountDownText(){return countDownText;}

}

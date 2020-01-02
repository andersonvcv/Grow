package com.vini.grow;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> timerText = new MutableLiveData<>();
    private MutableLiveData<String> buttonText = new MutableLiveData<>();
    private MutableLiveData<String> spinner_iTemSelected = new MutableLiveData<>();

    private boolean isTimerRunning;
    private long startTime;
    private int seekbarCountDownMinutes;

    private Handler countUpHandler = new Handler();
    private CountDownTimer countDownTimer;
    private boolean flagCountDownInicialization = true;

    // / Updates timer label every second
    private Runnable countUpRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;

            // update LiveData
            timerText.setValue(String.format("%d:%02d.%02d", hours, minutes, seconds));
            countUpHandler.postDelayed(countUpRunnable, 1000);
        }
    };

    //CONSTs
    private final String BT_TEXT_RUNNING = "STOP";
    private final String BT_TEXT_STOPPED = "RUN";
    private final String BT_TEXT_DEFAULT = "0:00.00";
    private final String SPINNER_TEXT_COUNTUP = "Count Up";
    private final String SPINNER_TEXT_COUNTDOWN = "Count Down";


    public MainActivityViewModel(){
        isTimerRunning = false;
        startTime = 0;
        seekbarCountDownMinutes = 60;

        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {}
            @Override
            public void onFinish() {}
        };

        timerText.setValue(BT_TEXT_DEFAULT);
        buttonText.setValue(BT_TEXT_STOPPED);
        spinner_iTemSelected.setValue(SPINNER_TEXT_COUNTUP);
    }

    public MutableLiveData<String> getTimerText() {
        return timerText;
    }
    public MutableLiveData<String> getButtonText() {
        return buttonText;
    }
    public MutableLiveData<String> getSpinner_iTemSelected() {
        return spinner_iTemSelected;
    }

    public void buttonOnClick(){
        isTimerRunning = !isTimerRunning;

        if (isTimerRunning){
            buttonText.setValue(BT_TEXT_RUNNING);

            if (spinner_iTemSelected.getValue().equals(SPINNER_TEXT_COUNTUP)){
                timerText.setValue(BT_TEXT_DEFAULT);

                startTime = System.currentTimeMillis();
                countUpHandler.removeCallbacks(countUpRunnable);
                countUpHandler.postDelayed(countUpRunnable, 1000); // Updates timer label every second

            } else if (spinner_iTemSelected.getValue().equals(SPINNER_TEXT_COUNTDOWN)){

                countDownTimer = new CountDownTimer(seekbarCountDownMinutes * 1000 * 60, 1000){
                    @Override
                    public void onTick(long millisUntillFinished) {
                        int seconds = (int) (millisUntillFinished / 1000);
                        int minutes = seconds / 60;
                        int hours = minutes / 60;
                        seconds = seconds % 60;
                        minutes = minutes % 60;
                        timerText.setValue(String.format("%d:%02d.%02d", hours, minutes, seconds));
                    }

                    @Override
                    public void onFinish() {
                        buttonText.setValue(BT_TEXT_STOPPED);
                    }
                }.start();
            }
        } else if (!isTimerRunning){
            buttonText.setValue(BT_TEXT_STOPPED);

            // Stop Handler/CountDownTimer
            countUpHandler.removeCallbacks(countUpRunnable);
            countDownTimer.cancel();
        }
    }

    public void spinnerOnSelectedItem(AdapterView<?> parent, View view, int pos, long id){
        spinner_iTemSelected.setValue(parent.getSelectedItem().toString());

        if (spinner_iTemSelected.getValue().equals(SPINNER_TEXT_COUNTDOWN)) {
            int minutes = seekbarCountDownMinutes;
            int hours = seekbarCountDownMinutes / 60;
            minutes = minutes % 60;
            timerText.setValue(String.format("%d:%02d.00", hours, minutes));
        }
    }

    public void seekbarOnProgressChanged(SeekBar view, int pos, boolean fromUser){

        if (!isTimerRunning && spinner_iTemSelected.getValue().equals(SPINNER_TEXT_COUNTDOWN)){
            seekbarCountDownMinutes = pos; // to setup CountDown Timer

            // Update timer_text with countdown timer setup
            int minutes = pos;
            int hours = minutes / 60;
            minutes = minutes % 60;

            timerText.setValue(String.format("%d:%02d.00", hours, minutes));
        }
    }

    public void timerTextOnCLick(){
        if (!isTimerRunning)
            timerText.setValue("0:00.00");
    }
}

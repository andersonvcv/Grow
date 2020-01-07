package com.vini.grow;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel implements LifecycleObserver {

    private MutableLiveData<String> timerText = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTimerRunning = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTimerCountingUp = new MutableLiveData<>();
    private MutableLiveData<String> toToast = new MutableLiveData<>();

    private Handler countUpHandler = new Handler();
    private CountDownTimer countDownTimer;
    private long startTime;
    private int seekbarCountDownMinutes;

    // Singleton to get resources
    ResourcesProvider resourcesProvider = ResourcesProvider.getInstance();

    // / Updates timer label every second
    private Runnable countUpRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            updateTimerText(millis);

            countUpHandler.postDelayed(countUpRunnable, 1000);
        }
    };

    public MainActivityViewModel(){

        // Private variables
        isTimerRunning.setValue(false);
        startTime = 0;
        seekbarCountDownMinutes = 60;

        // LiveData variables
        updateTimerText(resourcesProvider.getStringResource(R.string.spinner_defaultTime));
        isTimerCountingUp.setValue(true);

        // Initialization is necessary in order to cancel without crashing
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {}
            @Override
            public void onFinish() {}
        };
    }

    public MutableLiveData<String> getTimerText() {
        return timerText;
    }
    public MutableLiveData<Boolean> getIsTimerRunning() {
        return isTimerRunning;
    }
    public MutableLiveData<Boolean> getIsTimerCountingUp() {
        return isTimerCountingUp;
    }
    public MutableLiveData<String> getToToast() {
        return toToast;
    }

    public void buttonOnClick(){
        isTimerRunning.setValue(!isTimerRunning.getValue());
        if (isTimerRunning.getValue()){

            if (isTimerCountingUp.getValue()) {
                updateTimerText(resourcesProvider.getStringResource(R.string.spinner_defaultTime));

                startTime = System.currentTimeMillis();
                countUpHandler.removeCallbacks(countUpRunnable);
                countUpHandler.postDelayed(countUpRunnable, 1000); // Updates timer label every second

            } else if (!isTimerCountingUp.getValue()){
                countDownTimer = new CountDownTimer(seekbarCountDownMinutes * 1000 * 60, 1000){
                    @Override
                    public void onTick(long millisUntillFinished) {
                        updateTimerText(millisUntillFinished);
                    }

                    @Override
                    public void onFinish() {
                        isTimerRunning.setValue(!isTimerRunning.getValue());
                    }
                }.start();
            }
        } else if (!isTimerRunning.getValue()){

            // Stop Handler/CountDownTimer
            countUpHandler.removeCallbacks(countUpRunnable);
            countDownTimer.cancel();
        }
    }

    public void getSpinnerSelectedItem(AdapterView<?> parent, View view, int pos, long id){
        // get spinner value
        isTimerCountingUp.setValue(parent.getSelectedItem().toString().equals(resourcesProvider.getStringResource(R.string.spinner_count_up))); //SPINNER_TEXT_COUNTUP

        // update timerText with correct values when changing spinner value
        if (!isTimerCountingUp.getValue() && !isTimerRunning.getValue()){
            updateTimerText(seekbarCountDownMinutes);

        } else if (isTimerCountingUp.getValue() && !isTimerRunning.getValue()){
            updateTimerText(resourcesProvider.getStringResource(R.string.spinner_defaultTime));
        }
    }

    public void seekbarOnProgressChanged(SeekBar view, int pos, boolean fromUser){
        // Update timer_text with countdown timer setup
        if (!isTimerRunning.getValue() && !isTimerCountingUp.getValue()){
            seekbarCountDownMinutes = pos; // to setup CountDown Timer
            updateTimerText(pos);
        }
    }

    public void timerTextOnCLick(){
        if (!isTimerRunning.getValue() && isTimerCountingUp.getValue())
            updateTimerText(resourcesProvider.getStringResource(R.string.spinner_defaultTime));
    }

    private void updateTimerText(int min){
            int minutes = min;
            int hours = minutes / 60;
            minutes = minutes % 60;

            timerText.setValue(String.format("%d:%02d.00", hours, minutes));
    }

    private void updateTimerText(long miliseconds){
        int seconds = (int) (miliseconds / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        timerText.setValue(String.format("%d:%02d.%02d", hours, minutes, seconds));
    }

    private void updateTimerText(String string){
        timerText.setValue(string);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded(){
        Log.i("testLifecycle", "onAppBackgrounded: ");
        toToast.setValue("Backgrounded");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForegrounded(){
        Log.i("testLifecycle", "onAppForegrounded: ");
        toToast.setValue("Foregrounded");
    }
}
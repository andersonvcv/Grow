package com.vini.grow;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> taskRunTimeText = new MutableLiveData<>();
    private MutableLiveData<String> taskButtonText = new MutableLiveData<>();

    private boolean isTaskRunning;
    private long startTime;
    private String spinner_iTemSelected;

    private Handler timeHandler = new Handler();
    private CountDownTimer countDownTimer = new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {

        }
    };

    //CONSTs
    private final String TASKBUTTON_RUNNING = "STOP";
    private final String TASKBUTTON_STOPPED = "RUN";

    // Count up
    private Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;

            // update LiveData
            taskRunTimeText.setValue(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            timeHandler.postDelayed(updateTimeTask, 1000);
        }
    };

    public MainActivityViewModel(){
        // Initialization
        isTaskRunning = false;
        startTime = 0;

        taskRunTimeText.setValue("00:00:00");
        taskButtonText.setValue(TASKBUTTON_STOPPED);
    }

    public MutableLiveData<String> getTaskRunTimeText() {
        return taskRunTimeText;
    }

    public MutableLiveData<String> getTaskButtonText() {
        return taskButtonText;
    }

    public void taskButtonOnClick(){
        isTaskRunning = !isTaskRunning;

        if (isTaskRunning){
            taskButtonText.setValue(TASKBUTTON_RUNNING);

            if (spinner_iTemSelected.equals("Count Up")){
                // Updates time label every second
                startTime = System.currentTimeMillis();
                timeHandler.removeCallbacks(updateTimeTask);
                timeHandler.postDelayed(updateTimeTask, 1000);

            } else if (spinner_iTemSelected.equals("Count Down")){
                countDownTimer = new CountDownTimer(10000, 1000){
                    @Override
                    public void onTick(long millisUntillFinished) {
                        int seconds = (int) (millisUntillFinished / 1000);
                        int minutes = seconds / 60;
                        int hours = minutes / 60;
                        seconds = seconds % 60;
                        minutes = minutes % 60;
                        taskRunTimeText.setValue(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                    }

                    @Override
                    public void onFinish() {
                        taskButtonText.setValue(TASKBUTTON_STOPPED);
                    }
                }.start();
            }
        } else {
            taskButtonText.setValue(TASKBUTTON_STOPPED);
            timeHandler.removeCallbacks(updateTimeTask);
            countDownTimer.cancel();
        }
    }

    public void spinnerOnSelectedItem(AdapterView<?> parent, View view, int pos, long id){
        spinner_iTemSelected = parent.getSelectedItem().toString();
    }
}

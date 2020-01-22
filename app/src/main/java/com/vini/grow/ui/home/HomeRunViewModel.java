package com.vini.grow.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vini.grow.ui.home.utils.Chronometer;
import com.vini.grow.ui.home.utils.ChronometerPresenter;
import com.vini.grow.ui.home.utils.IChronometerPresenter;

public class HomeRunViewModel extends ViewModel {

    private MutableLiveData<String> navigate;

    private Chronometer chronometer;
    private IChronometerPresenter presenter;
    private MutableLiveData<String> timerText;
    private final String STRINGFORMAT = "%d:%02d.%02d";
    private LiveData<Boolean> isTimerRunning;
    private LiveData<String> buttonText;

    private boolean chronometerIsSetToCountUp;
    private int countDownTime;

    public HomeRunViewModel() {
        navigate = new MutableLiveData<>();
        timerText = new MutableLiveData<>();
        buttonText = new MutableLiveData<>();

        presenter = new ChronometerPresenter();
        buttonText = chronometer.getCountDownText();

    }

    public void setValuesFromPreviousFragment(boolean chronometerIsSetToCountUp, int countDownTime){
        this.chronometerIsSetToCountUp = chronometerIsSetToCountUp;
        this.countDownTime = countDownTime;

        chronometer = new Chronometer(timerText, presenter, STRINGFORMAT);
        isTimerRunning = chronometer.getIsTimerRunning();
        if (chronometerIsSetToCountUp){
            chronometer.startCountUp();
        } else {
            chronometer.startCountDown(countDownTime);
        }
    }

    public void buttonOnClick(){
        if (isTimerRunning.getValue()){
            chronometer.stop();
            navigate.setValue("fail");
        } else {
            navigate.setValue("win");
        }
    }

    public LiveData<String> getNavigate() {
        return navigate;
    }
    public LiveData<String> getTimerText() {
        return timerText;
    }
    public LiveData<String> getButtonText() {
        return buttonText;
    }
}
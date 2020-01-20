package com.vini.grow.viewmodel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vini.grow.R;
import com.vini.grow.repository.Repository;
import com.vini.grow.util.ResourcesProvider;
import com.vini.grow.viewmodel.utils.Chronometer;
import com.vini.grow.viewmodel.utils.ChronometerPresenter;
import com.vini.grow.viewmodel.utils.IChronometerPresenter;

public class MainActivityViewModel extends ViewModel implements LifecycleObserver{
    // Singleton to get resources
    // Class can be unloaded if reference is not kept in viewmodel
    ResourcesProvider resourcesProvider = ResourcesProvider.getInstance();

    private final String STRINGFORMAT = "%d:%02d.%02d";

    private Repository repository;
    private IChronometerPresenter presenter;
    private Chronometer chronometer;
    private int seekbarCountDownMinutes;

    private MutableLiveData<String> timerText;
    private MutableLiveData<Boolean> isTimerRunning;
    private MutableLiveData<Boolean> isSpinnerSetToCountUp;
    private MutableLiveData<String> toToast;
    private MutableLiveData<String> userCoins ;

    public MainActivityViewModel(){
        timerText = new MutableLiveData<>();
        isTimerRunning = new MutableLiveData<>();
        isSpinnerSetToCountUp = new MutableLiveData<>();
        toToast = new MutableLiveData<>();
        userCoins = new MutableLiveData<>();

        repository = new Repository();
        presenter = new ChronometerPresenter();
        chronometer = new Chronometer(timerText, presenter, STRINGFORMAT, isTimerRunning);
        presenter.updateTimerText(timerText, 0, STRINGFORMAT);

        seekbarCountDownMinutes = 25;
        isSpinnerSetToCountUp.setValue(true);
        userCoins.setValue(String.valueOf(0));
    }

    public void buttonOnClick(){
        if (!chronometer.isRunning()){
            if (isSpinnerSetToCountUp.getValue()){
                chronometer.startCountUp();
            } else {
                chronometer.startCountDown(seekbarCountDownMinutes);
            }
        } else {
            chronometer.stop();
            if (isSpinnerSetToCountUp.getValue()){
                presenter.updateTimerText(timerText, 0, STRINGFORMAT);
            }
            else{
                presenter.updateTimerText(timerText, seekbarCountDownMinutes, STRINGFORMAT);
            }
        }
    }

    // Spinner to select countdown/countup
    public void getSpinnerSelectedItem(AdapterView<?> parent, View view, int pos, long id){
        isSpinnerSetToCountUp.setValue(parent.getSelectedItem().toString().equals(resourcesProvider.getStringResource(R.string.spinner_count_up))); //SPINNER_TEXT_COUNTUP
        // update timerText with correct values when changing spinner value
        if (!isSpinnerSetToCountUp.getValue() && !isTimerRunning.getValue()){
            presenter.updateTimerText(timerText, seekbarCountDownMinutes, STRINGFORMAT);
        } else if (isSpinnerSetToCountUp.getValue() && !isTimerRunning.getValue()){
            presenter.updateTimerText(timerText, 0, STRINGFORMAT);
        }
    }

    // Seekbar to select countdow time
    public void seekbarOnProgressChanged(SeekBar view, int pos, boolean fromUser){
        // Update timer_text with countdown timer setup
        if (!isTimerRunning.getValue() && !isSpinnerSetToCountUp.getValue()){
            seekbarCountDownMinutes = pos; // to setup CountDown Timer
            presenter.updateTimerText(timerText, seekbarCountDownMinutes, STRINGFORMAT);
        }
    }


//    private void updateUserCoins(long reward){
//        userCoins.setValue(String.valueOf(Long.parseLong(userCoins.getValue())+reward));
//    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    private void onAppBackgrounded(){
//        Log.i("testLifecycle", "onAppBackgrounded: ");
//        toToast.setValue("Backgrounded");
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    private void onAppForegrounded(){
//        Log.i("testLifecycle", "onAppForegrounded: ");
//        toToast.setValue("Foregrounded");
//    }

    public LiveData<String> getTimerText() {
        return timerText;
    }
    public LiveData<Boolean> getIsTimerRunning() {
        return isTimerRunning;
    }
    public LiveData<Boolean> getIsSpinnerSetToCountUp() {
        return isSpinnerSetToCountUp;
    }
    public LiveData<String> getToToast() {
        return toToast;
    }
    public LiveData<String> getUserCoins() {
        return userCoins;
    }

    //TODO: ADD REPOSITORY WRAPPERS
    // LiveData repository wrappers so the view can observe


}
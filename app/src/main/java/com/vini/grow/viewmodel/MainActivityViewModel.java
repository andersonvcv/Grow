package com.vini.grow.viewmodel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vini.grow.R;
import com.vini.grow.repository.Repository;
import com.vini.grow.util.ResourcesProvider;
import com.vini.grow.viewmodel.utils.Chronometer;
import com.vini.grow.viewmodel.utils.Presenter;

public class MainActivityViewModel extends ViewModel implements LifecycleObserver{
    // Singleton to get resources
    // Class can be unloaded if reference is not kept in ViewModel
    ResourcesProvider resourcesProvider = ResourcesProvider.getInstance();

    private Repository repository;
    private Presenter presenter;

    private MutableLiveData<String> timerText = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTimerRunning = new MutableLiveData<>();
    private MutableLiveData<Boolean> isSpinnerSetToCountUp = new MutableLiveData<>();
    private MutableLiveData<String> toToast = new MutableLiveData<>();
    private MutableLiveData<String> userCoins = new MutableLiveData<>();

    private final String STRINGFORMAT = "%d:%02d.%02d";
    private Chronometer chronometer = new Chronometer(timerText, STRINGFORMAT, isTimerRunning);

    private int seekbarCountDownMinutes;

    public MainActivityViewModel(){
        repository = new Repository();

        presenter = new Presenter();
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

    // Spinner to select countdown/countUp
    public void getSpinnerSelectedItem(AdapterView<?> parent, View view, int pos, long id){
        isSpinnerSetToCountUp.setValue(parent.getSelectedItem().toString().equals(resourcesProvider.getStringResource(R.string.spinner_count_up))); //SPINNER_TEXT_COUNTUP
        // update timerText with correct values when changing spinner value
        if (!isSpinnerSetToCountUp.getValue() && !isTimerRunning.getValue()){
            presenter.updateTimerText(timerText, seekbarCountDownMinutes, STRINGFORMAT);
        } else if (isSpinnerSetToCountUp.getValue() && !isTimerRunning.getValue()){
            presenter.updateTimerText(timerText, 0, STRINGFORMAT);
        }
    }

    // Seekbar to select countdown time
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

    public MutableLiveData<String> getTimerText() {
        return timerText;
    }
    public MutableLiveData<Boolean> getIsTimerRunning() {
        return isTimerRunning;
    }
    public MutableLiveData<Boolean> getIsSpinnerSetToCountUp() {
        return isSpinnerSetToCountUp;
    }
    public MutableLiveData<String> getToToast() {
        return toToast;
    }
    public MutableLiveData<String> getUserCoins() {
        return userCoins;
    }

    //TODO: ADD REPOSITORY WRAPPERS
    // LiveData repository wrappers so the view can observe


}
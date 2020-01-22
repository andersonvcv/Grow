package com.vini.grow.ui.home;

import android.os.CountDownTimer;
import android.widget.SeekBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vini.grow.ui.home.utils.Presenter;
import com.vini.grow.util.ResourcesProvider;

public class HomeSharedViewModel extends ViewModel {

    private final String STRINGFORMAT = "%d:%02d.%02d";
    private final int TIMER_TEXT_INIT = 12; // you have to multiply this value by SEEKBAR_PROGRESS_STEP
    private final int COUNTDOWN_TO_CANCEL_VALUE = 10;
    private final int SEEKBAR_PROGRESS_STEP = 5;



    // SHARED VARIABLES
    // Singleton to get resources
    // Class can be unloaded if reference is not kept in viewmodel
    ResourcesProvider resourcesProvider = ResourcesProvider.getInstance();
    Presenter presenter = new Presenter();
    private MutableLiveData<Boolean> navigate = new MutableLiveData<>();
    private MutableLiveData<String> navigateTo = new MutableLiveData<>();
    private MutableLiveData<String> userCoins = new MutableLiveData<>();

    // HomeBegin Variables
    private MutableLiveData<String> timerTextHome = new MutableLiveData<>();

    // HomeRunning Variables
    private MutableLiveData<String> timerTextRunning = new MutableLiveData<>();
    private MutableLiveData<String> buttonTextRunning = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTimerRunning = new MutableLiveData<>();
    private int countDownTime;
    private MutableLiveData<Integer> seekBarTime = new MutableLiveData<>();
    private int buttonTextCountDown;
    private CountDownTimer countDownTimer;

    // HomeEnd Variables
    private MutableLiveData<String> victory = new MutableLiveData<>();

    public HomeSharedViewModel() {

        //HomeBegin
        presenter.updateTimerText(timerTextHome, TIMER_TEXT_INIT*SEEKBAR_PROGRESS_STEP, STRINGFORMAT);
        countDownTime = TIMER_TEXT_INIT*SEEKBAR_PROGRESS_STEP;

        //HomeRunning
        buttonTextRunning.setValue("Cancel");
        buttonTextCountDown = COUNTDOWN_TO_CANCEL_VALUE;
        isTimerRunning.setValue(false);
        seekBarTime.setValue(TIMER_TEXT_INIT);

        userCoins.setValue(String.valueOf(0));  // get from DB
        navigate.setValue(false);
    }

    // HomeBegin
    // Go to home_run and send bundle with data
    // Function to notify HomeFragment that button was clicked
    public void buttonHomeOnClick(){
        navigate("home_run");
        startCountDown(countDownTime);
    }

    // Seekbar to select countdown time
    public void seekbarOnProgressChanged(SeekBar view, int pos, boolean fromUser){
        seekBarTime.setValue(pos); // remember the last value

        countDownTime = pos*SEEKBAR_PROGRESS_STEP; // map to 5/5 seconds
        // Update timer_text with countdown timer setup
        presenter.updateTimerText(timerTextHome, countDownTime, STRINGFORMAT);
    }

    // HomeRunning
    public void buttonHomeRunOnClick(){
        if (isTimerRunning.getValue()){
            if (buttonTextCountDown >= 1){ // cancel
                stop();
                navigate("home");
            } else { // give up
                stop();
                victory.setValue("lose");
                navigate("home_end");
            }
        } else {
            victory.setValue("win");
            navigate("home_end");
        }

    }

    public void startCountDown(int minutes){
        isTimerRunning.setValue(true);
        presenter.updateTimerText(timerTextRunning, minutes, STRINGFORMAT);

        countDownTimer = new CountDownTimer(minutes * 1000 * 60, 1000){
            @Override
            public void onTick(long millisUntillFinished) {
                presenter.updateTimerText(timerTextRunning, millisUntillFinished, STRINGFORMAT);
                if (buttonTextCountDown >= 1){
                    buttonTextCountDown = buttonTextCountDown -1;
                    buttonTextRunning.setValue("Cancel (" + buttonTextCountDown + ")");
                }
                else if (buttonTextCountDown >= -1){
                    buttonTextCountDown = buttonTextCountDown -1;
                    buttonTextRunning.setValue("Give Up");
                }
            }

            @Override
            public void onFinish() {
                isTimerRunning.setValue(false);
                buttonTextCountDown = COUNTDOWN_TO_CANCEL_VALUE;
            }
        }.start();
    }

    public void stop(){
        countDownTimer.cancel();
        isTimerRunning.setValue(false);
        buttonTextCountDown = COUNTDOWN_TO_CANCEL_VALUE;
    }

    // Shared
    private void navigate(String destination){
        navigateTo.setValue(destination);
        navigate.setValue(true);
        navigate.setValue(false);
    }



    // HomeBegin getters
    public LiveData<String> getTimerTextHome() {
        return timerTextHome;
    }

    // HomeRunning getters
    public LiveData<String> getTimerTextRunning() {
        return timerTextRunning;
    }
    public LiveData<String> getButtonTextRunning() {
        return buttonTextRunning;
    }
    public LiveData<Integer> getSeekBarTime() {
        return seekBarTime;
    }

    // HomeEnd getters
    public LiveData<String> getVictory() {
        return victory;
    }

    public LiveData<String> getUserCoins() {
        return userCoins;
    }
    public LiveData<Boolean> getNavigate() {
        return navigate;
    }
    public LiveData<String> getNavigateTo() {
        return navigateTo;
    }

}

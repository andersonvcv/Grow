package com.vini.grow.ui.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import com.vini.grow.R;
import com.vini.grow.repository.Repository;
import com.vini.grow.ui.home.utils.Chronometer;
import com.vini.grow.ui.home.utils.ChronometerPresenter;
import com.vini.grow.ui.home.utils.IChronometerPresenter;
import com.vini.grow.util.ResourcesProvider;

public class HomeViewModel extends ViewModel {
    // Singleton to get resources
    // Class can be unloaded if reference is not kept in viewmodel
    ResourcesProvider resourcesProvider = ResourcesProvider.getInstance();

    private final String STRINGFORMAT = "%d:%02d.%02d";

    private IChronometerPresenter presenter;

    private MutableLiveData<String> navigate;
    private MutableLiveData<Integer> countDownTime;
    private MutableLiveData<String> timerText;
    private MutableLiveData<Boolean> isSpinnerSetToCountUp;
    private MutableLiveData<String> userCoins ;



    public HomeViewModel() {
        navigate = new MutableLiveData<>();
        timerText = new MutableLiveData<>();
        isSpinnerSetToCountUp = new MutableLiveData<>();
        userCoins = new MutableLiveData<>();
        countDownTime = new MutableLiveData<>();

        presenter = new ChronometerPresenter();
        presenter.updateTimerText(timerText, 0, STRINGFORMAT);

        countDownTime.setValue(25);
        isSpinnerSetToCountUp.setValue(false);
        userCoins.setValue(String.valueOf(0));
    }

    // Go to home_run and send bundle with data
    // Function to notify HomeFragment that button was clicked
    public void buttonOnClick(){
        navigate.setValue("");
    }

    // Spinner to select countdown/countup
    public void getSpinnerSelectedItem(AdapterView<?> parent, View view, int pos, long id){
        isSpinnerSetToCountUp.setValue(parent.getSelectedItem().toString().equals(resourcesProvider.getStringResource(R.string.spinner_count_up))); //SPINNER_TEXT_COUNTUP
        // update timerText with correct values when changing spinner value
        if (!isSpinnerSetToCountUp.getValue()){
            presenter.updateTimerText(timerText, countDownTime.getValue(), STRINGFORMAT);
        } else if (isSpinnerSetToCountUp.getValue()){
            presenter.updateTimerText(timerText, 0, STRINGFORMAT);
        }
    }

    // Seekbar to select countdown time
    public void seekbarOnProgressChanged(SeekBar view, int pos, boolean fromUser){
        // Update timer_text with countdown timer setup
        if (!isSpinnerSetToCountUp.getValue()){
            countDownTime.setValue(pos);
            presenter.updateTimerText(timerText, countDownTime.getValue(), STRINGFORMAT);
        }
    }

    public LiveData<String> getTimerText() {
        return timerText;
    }
    public LiveData<Boolean> getIsSpinnerSetToCountUp() {
        return isSpinnerSetToCountUp;
    }
    public LiveData<String> getUserCoins() {
        return userCoins;
    }
    public LiveData<String> getNavigate() {
        return navigate;
    }
    public LiveData<Integer> getCountDownTime() {
        return countDownTime;
    }
}
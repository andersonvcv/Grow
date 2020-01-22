package com.vini.grow.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeEndViewModel extends ViewModel {

    private MutableLiveData<String> victory;

    public HomeEndViewModel() {
        victory = new MutableLiveData<>();
    }

    public void setValuesFromPreviousFragment(String victory){
        this.victory.setValue(victory);
    }

    public LiveData<String> getVictory() {
        return victory;
    }
}
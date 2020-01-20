package com.vini.grow.viewmodel.utils;

import androidx.lifecycle.MutableLiveData;

public class ChronometerPresenter implements IChronometerPresenter{
    public ChronometerPresenter(){}

    // Update View livedata through data binding
    public void updateTimerText(MutableLiveData mutableLiveData, int min, String STRINGFORMAT){
        int minutes = min;
        int hours = minutes / 60;
        minutes = minutes % 60;
        mutableLiveData.setValue(String.format(STRINGFORMAT, hours, minutes, 0));
    }

    public void updateTimerText(MutableLiveData mutableLiveData, long miliseconds, String STRINGFORMAT){
        int seconds = (int) (miliseconds / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        mutableLiveData.setValue(String.format(STRINGFORMAT, hours, minutes, seconds));
    }
}

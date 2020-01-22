package com.vini.grow.ui.home.utils;

import androidx.lifecycle.MutableLiveData;

public class Presenter {
    public Presenter(){}

    // Update View livedata through data binding
    public void updateTimerText(MutableLiveData<String> mutableLiveData, int min, String STRINGFORMAT){
        int minutes = min;
        int hours = minutes / 60;
        minutes = minutes % 60;
        mutableLiveData.setValue(String.format(STRINGFORMAT, hours, minutes, 0));
    }

    public void updateTimerText(MutableLiveData<String> mutableLiveData, long miliseconds, String STRINGFORMAT){
        int seconds = (int) (miliseconds / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        mutableLiveData.setValue(String.format(STRINGFORMAT, hours, minutes, seconds));
    }
}

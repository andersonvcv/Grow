package com.vini.grow.ui.home.utils;

import androidx.lifecycle.MutableLiveData;

public interface IChronometerPresenter {

    public void updateTimerText(MutableLiveData mutableLiveData, int min, String STRINGFORMAT);
    public void updateTimerText(MutableLiveData mutableLiveData, long min, String STRINGFORMAT);
}
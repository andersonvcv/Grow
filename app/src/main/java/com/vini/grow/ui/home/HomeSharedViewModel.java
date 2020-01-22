package com.vini.grow.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeSharedViewModel extends ViewModel {

    MutableLiveData<String> buttonText;

    public HomeSharedViewModel() {
    }

    public MutableLiveData<String> getButtonText() {
        return buttonText;
    }
}

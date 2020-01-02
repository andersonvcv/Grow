package com.vini.grow;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

//    @BindingAdapter("android:text")
//    public static void setText(View view){
//        if (view.getId() == R.id.tv_timer){
//
//        }
//    }

    @BindingAdapter(value = {"android:visibility", "android:spinnerTimerMode"}, requireAll = false)
    public static void setVisibility(View view, Boolean isTimerRunning, Boolean isTimerCountingUp){
        if (!isTimerRunning && !isTimerCountingUp){
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }



}

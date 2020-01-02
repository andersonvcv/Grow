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

    @BindingAdapter(value = {"android:visibility", "android:spinnerItemSelected"}, requireAll = false)
    public static void setVisibility(View view, String buttonText, String spinnerItemSelected){
        if (buttonText.equals("RUN") && spinnerItemSelected.equals("Count Down")){
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }



}

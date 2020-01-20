package com.vini.grow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.vini.grow.databinding.FragmentMainBinding;
import com.vini.grow.viewmodel.MainActivityViewModel;


public class MainFragment extends Fragment {

    private MainActivityViewModel mainActivityViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Set to the same viewmodel everytime
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // add viewmodel as Lifecycle Observer
//        getLifecycle().addObserver(mainActivityViewModel);

        // dataBinding
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(mainActivityViewModel);

        return binding.getRoot();

    }
}

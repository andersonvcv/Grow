package com.vini.grow.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import com.vini.grow.R;
import com.vini.grow.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        // Set the same ViewModel everytime
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        // dataBiding
        FragmentHomeBinding biding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);

        biding.setViewmodel(homeViewModel);
        biding.setLifecycleOwner(this);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);


        homeViewModel.getToToast().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                navController.navigate(R.id.action_nav_home_to_nav_gallery);
            }
        });




//        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        View root = biding.getRoot();
        return root;
    }


}
package com.vini.grow.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.vini.grow.R;
import com.vini.grow.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeSharedViewModel homeSharedViewModel;
    private HomeViewModel homeViewModel;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Set the same ViewModel everytime
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        // dataBiding
        final FragmentHomeBinding biding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        biding.setViewmodel(homeViewModel);
        biding.setLifecycleOwner(this);

        // Navigate to next fragment
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        homeViewModel.getNavigate().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("countUp", homeViewModel.getIsSpinnerSetToCountUp().getValue());
                bundle.putInt("countDownTime", homeViewModel.getCountDownTime().getValue());
                navController.navigate(R.id.action_nav_home_to_nav_home_run, bundle);
            }
        });

        View root = biding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        homeSharedViewModel = ViewModelProviders.of(getActivity()).get(HomeSharedViewModel.class);
    }
}
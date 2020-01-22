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
import com.vini.grow.ui.home.utils.DrawerLocker;

public class HomeFragment extends Fragment {


    private HomeSharedViewModel homeSharedViewModel;
    private NavController navController;
    private FragmentHomeBinding biding;
    private DrawerLocker drawerLocker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Enable Drawer in this fragment
        drawerLocker = (DrawerLocker) getActivity();
        drawerLocker.setDrawerEnabled(true);

        // dataBiding
        biding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        View root = biding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Creating the viewmodel here we make sure that the OnCreate method of the underlying activity
        // was finished
        homeSharedViewModel = ViewModelProviders.of(getActivity()).get(HomeSharedViewModel.class);

        // dataBiding here because we need viewmodel instantiated
        biding.setViewmodel(homeSharedViewModel);
        // set to view's lifecycle and not to the instance
        biding.setLifecycleOwner(getViewLifecycleOwner());

        // Navigate to next fragment
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        homeSharedViewModel.getNavigate().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean navigate) {
                if (navigate)
                    navController.navigate(R.id.action_nav_home_to_nav_home_run);
            }
        });
    }
}
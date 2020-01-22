package com.vini.grow.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.vini.grow.R;
import com.vini.grow.databinding.FragmentHomeRunBinding;
import com.vini.grow.ui.home.utils.DrawerLocker;

public class HomeRunFragment extends Fragment {

    private HomeSharedViewModel homeSharedViewModel;
    private NavController navController;
    private FragmentHomeRunBinding biding;
    private DrawerLocker drawerLocker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        drawerLocker = (DrawerLocker) getActivity();
        drawerLocker.setDrawerEnabled(false);

        // dataBiding
        biding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_run, container, false);

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
                if (navigate) {
                    if (homeSharedViewModel.getNavigateTo().getValue().equals("home")) { // cancel
                        navController.navigate(R.id.action_nav_home_run_to_nav_home);
                    } else if (homeSharedViewModel.getNavigateTo().getValue().equals("home_end")) { // give up
                        navController.navigate(R.id.action_nav_home_run_to_nav_home_end);
                    }
                }
            }
        });
    }

    // function used to call Activity OnBackPressed
    public void callParentActivity(){
        getActivity().onBackPressed();
    }

}
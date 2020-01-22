package com.vini.grow.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.vini.grow.R;
import com.vini.grow.databinding.FragmentHomeEndBinding;

public class HomeEndFragment extends Fragment {

    private HomeEndViewModel homeEndViewModel;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Set the same ViewModel everytime
        homeEndViewModel = ViewModelProviders.of(this).get(HomeEndViewModel.class);

        // dataBiding
        FragmentHomeEndBinding biding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_end,
                container, false);

        biding.setViewmodel(homeEndViewModel);
        biding.setLifecycleOwner(this);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        // Get data from previous fragment
        homeEndViewModel.setValuesFromPreviousFragment(getArguments().getString("victory"));

        // Navigate to next fragment
//        homeEndViewModel.getNavigate().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                Bundle bundle = new Bundle();
//                bundle.putString("victory", homeRunViewModel.getNavigate().getValue());
//                navController.navigate(R.id.action_nav_home_run_to_nav_home_end, bundle);
//            }
//        });

        View root = biding.getRoot();
        return root;
    }
}
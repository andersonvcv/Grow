package com.vini.grow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.vini.grow.util.ResourcesProvider;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set context to singleton in order to get resources in the viewmodel
        ResourcesProvider.getInstance().setApplicationContext(getApplicationContext());

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_view);
        setSupportActionBar(toolbar);

        // Set Drawer
        drawerLayout = findViewById(R.id.drawer_layout);

        // Navigation
        NavigationView navigationView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

//        ActionBarDrawerToggle actionBarDrawerToggle =new ActionBarDrawerToggle(this,
//                drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();

//        if (savedInstanceState==null) {
//            // Open fragment
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
//
//            // Select item
//            navigationView.setCheckedItem(R.id.nav_analytics);
//        }

        // Show toast
//        mainActivityViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> users) {
//                Toast.makeText(getApplicationContext(), users.get(1).getFisrtName(), Toast.LENGTH_LONG).show();
//            }
//        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(
                Navigation.findNavController(this, R.id.fragment),
                drawerLayout);
    }
    //    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()){
//            case R.id.nav_analytics:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
//                break;
////            case R.id.nav_friends:
////                break;
////            case R.id.nav_settings:
////                break;
////            case R.id.nav_store:
////                break;
////            case R.id.nav_share:
////                break;
////            case R.id.nav_controller_view_tag:
////                break;
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public void onBackPressed(){
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else{
//            super.onBackPressed();
//        }
//    }
}

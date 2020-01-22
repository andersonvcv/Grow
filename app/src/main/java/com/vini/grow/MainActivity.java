package com.vini.grow;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.vini.grow.ui.home.utils.DrawerLocker;
import com.vini.grow.util.ResourcesProvider;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity implements DrawerLocker {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set context to singleton in order to get resources in the viewmodel
        ResourcesProvider.getInstance().setApplicationContext(getApplicationContext());

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_home_end,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawerLayout)
                .build();

        // Navigation
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // Enable or disable drawer
    @Override
    public void setDrawerEnabled(boolean enabled){
        drawerLayout.setDrawerLockMode(enabled ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(enabled);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

    }
}

package com.example.yumplanner.presentation.Home.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.yumplanner.presentation.plan.view.CalenderFragment;
import com.example.yumplanner.FavFragment;
import com.example.yumplanner.ProfileFragment;
import com.example.yumplanner.R;
import com.example.yumplanner.presentation.Search.view.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment homeFragment;
    private Fragment searchFragment;
    private Fragment favoritesFragment;
    private Fragment profileFragment;
    private Fragment activeFragment;
    private Fragment calenderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_activty);

        fragmentManager = getSupportFragmentManager();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        initializeFragments();
        setupBottomNavigation(bottomNav);
    }
    private void initializeFragments() {
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        favoritesFragment = new FavFragment();
        profileFragment = new ProfileFragment();
        calenderFragment =new CalenderFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, homeFragment, "HOME")
                .add(R.id.fragment_container, searchFragment, "SEARCH").hide(searchFragment)
                .add(R.id.fragment_container, favoritesFragment, "FAVORITES").hide(favoritesFragment)
                .add(R.id.fragment_container, profileFragment, "PROFILE").hide(profileFragment)
                .add(R.id.fragment_container, calenderFragment,"CALENDER").hide(calenderFragment)
                .commit();

        activeFragment = homeFragment;
    }
    private void setupBottomNavigation(BottomNavigationView bottomNav) {
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.homeFragment2) {
                selectedFragment = homeFragment;
            } else if (itemId == R.id.searchFragment) {
                selectedFragment = searchFragment;
            } else if (itemId == R.id.favFragment) {
                selectedFragment = favoritesFragment;
            } else if (itemId == R.id.profileFragment2) {
                selectedFragment = profileFragment;
            } else if (itemId==R.id.nav_calender) {
                selectedFragment=calenderFragment;

            }

            if (selectedFragment != null && selectedFragment != activeFragment) {
                fragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(selectedFragment)
                        .commit();

                activeFragment = selectedFragment;
            }

            return true;
        });

        bottomNav.setOnItemReselectedListener(item -> {

        });
    }


}

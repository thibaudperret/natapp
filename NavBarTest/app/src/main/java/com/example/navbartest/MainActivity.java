package com.example.navbartest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationViewEx navBar;
    private Fragment fragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Jobino");

        navBar = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation);
        setBottomBarSettings();
    }

    private void setBottomBarSettings() {
        navBar.enableAnimation(false);
        navBar.enableShiftingMode(false);
        navBar.enableItemShiftingMode(false);
        navBar.setTextVisibility(false);

        manager = getFragmentManager();
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_search:
                        fragment = new SearchFragment();
                        setTitle("Search");
                        break;
                    case R.id.action_add:
                        fragment = new AddFragment();
                        setTitle("Add");
                        break;
                    case R.id.action_inbox:
                        fragment = new InboxFragment();
                        setTitle("Inbox");
                        break;
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
                        setTitle("Profile");
                        break;
                }
                final FragmentTransaction transaction = manager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.main_frame, fragment).commit();
                return true;
            }
        });
    }
}

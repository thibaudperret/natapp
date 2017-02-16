package com.example.navbartest;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationViewEx navBar;
    private Fragment fragment;
    private FragmentManager manager;

    private Button button_search;
    private Button button_add;
    private Button button_inbox;
    private Button button_profile;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Jobino");

        title = (TextView) findViewById(R.id.title);
        button_search = (Button) findViewById(R.id.button_search);
        button_add = (Button) findViewById(R.id.button_add);
        button_inbox = (Button) findViewById(R.id.button_inbox);
        button_profile = (Button) findViewById(R.id.button_profile);
        setButtonListener();

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

    private void setButtonListener(){
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new SearchFragment();
                setAndDeleteLayout(fragment);
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AddFragment();
                setAndDeleteLayout(fragment);
            }
        });

        button_inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new InboxFragment();
                setAndDeleteLayout(fragment);
            }
        });

        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new ProfileFragment();
                setAndDeleteLayout(fragment);
            }
        });
    }

    private void setAndDeleteLayout(Fragment fragment){
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.main_frame, fragment).commit();
        ViewGroup layout = (ViewGroup) button_search.getParent();
        layout.removeView(button_search);
        layout.removeView(button_add);
        layout.removeView(button_inbox);
        layout.removeView(button_profile);
        layout = (ViewGroup) title.getParent();
        layout.removeView(title);
    }
}

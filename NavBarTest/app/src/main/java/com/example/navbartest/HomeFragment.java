package com.example.navbartest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment{

    private Button button_search;
    private Button button_add;
    private Button button_inbox;
    private Button button_profile;
    private TextView title;
    private Fragment fragment;
    private FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MainActivity m = (MainActivity) getActivity();
        m.setTitle("Jobino");

        title = (TextView) view.findViewById(R.id.title);
        button_search = (Button) view.findViewById(R.id.button_search);
        button_add = (Button) view.findViewById(R.id.button_add);
        button_inbox = (Button) view.findViewById(R.id.button_inbox);
        button_profile = (Button) view.findViewById(R.id.button_profile);
        setButtonListener();

        return view;
    }

    private void setButtonListener(){
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new SearchFragment();
                setAndDeleteLayout(fragment, v);
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AddFragment();
                setAndDeleteLayout(fragment, v);
            }
        });

        button_inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new InboxFragment();
                setAndDeleteLayout(fragment, v);
            }
        });

        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new ProfileFragment();
                setAndDeleteLayout(fragment, v);
            }
        });
    }

    private void setAndDeleteLayout(Fragment fragment, View v){
        manager = getFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.main_frame, fragment).commit();
    }
}

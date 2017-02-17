package com.example.navbartest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class ProfileFragment extends Fragment{

    private ToggleButton seeHideButton;
    private LinearLayout commentsLayout;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        MainActivity m = (MainActivity) getActivity();
        m.setTitle("Profile");

        seeHideButton = (ToggleButton) view.findViewById(R.id.see_hide_toggle);
        commentsLayout = (LinearLayout) view.findViewById(R.id.user_comments);

        setToggleListener();

        return view;
    }

    private void setToggleListener() {
        seeHideButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    commentsLayout.setVisibility(View.VISIBLE);
                } else {
                    commentsLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}

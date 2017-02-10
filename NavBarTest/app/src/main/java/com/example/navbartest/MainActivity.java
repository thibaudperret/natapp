package com.example.navbartest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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

    private ToggleButton searchMode;
    private LinearLayout advancedView;
    private BottomNavigationViewEx navBar;
    private Button datePick;

    private CrystalRangeSeekbar priceRange;
    private TextView priceMin, priceMax;

    private Button locationPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchMode = (ToggleButton) findViewById(R.id.advanced_search_toggle);
        advancedView = (LinearLayout) findViewById(R.id.linear_scroll_view);
        navBar = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation);
        datePick = (Button) findViewById(R.id.date_pick);

        priceRange = (CrystalRangeSeekbar) findViewById(R.id.price_range);
        priceMin = (TextView) findViewById(R.id.price_min);
        priceMax = (TextView) findViewById(R.id.price_max);

        locationPick = (Button) findViewById(R.id.location_pick);

        setSearchModeListener();
        setBottomBarSettings();
        setDatePick();
        setRangeListener();
        setLocationPick();
    }

    private void setSearchModeListener() {
        searchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    advancedView.setVisibility(View.VISIBLE);
                } else {
                    advancedView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void setBottomBarSettings() {
        navBar.enableAnimation(false);
        navBar.enableShiftingMode(false);
        navBar.enableItemShiftingMode(false);
        navBar.setTextVisibility(false);
    }

    private void setDatePick() {
        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Toast t = Toast.makeText(getContext(), day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT);
            t.show();
        }
    }

    private void setRangeListener() {
        priceRange.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                priceMin.setText(minValue.toString());
                priceMax.setText(maxValue.toString());
            }
        });
    }

    private void setLocationPick() {
        locationPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fr = new MapDialogFragment();
                fr.show(getFragmentManager(), "locationPicker");
            }
        });
    }

    public static class MapDialogFragment extends DialogFragment {
        private MapFragment fragment;

        public MapDialogFragment() {
            fragment = new MapFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState) {
            View view = inf.inflate(R.layout.map_dialog, container, false);
            FragmentTransaction tr = getChildFragmentManager().beginTransaction();
            tr.add(R.id.map_view, fragment).commit();
            return view;
        }

        public MapFragment getFragment() {
            return fragment;
        }
    }
}

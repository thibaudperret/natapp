package com.example.navbartest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.MapFragment;

import io.apptik.widget.MultiSlider;

public class SearchFragment extends Fragment {

    private ToggleButton searchMode;
    private LinearLayout advancedView;
    private Button datePick;

    private MultiSlider priceRange;
    private TextView priceMin, priceMax;

    private Button locationPick;

    private static EditText date;

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        MainActivity m = (MainActivity) getActivity();
        m.setTitle("Search");

        searchMode = (ToggleButton) view.findViewById(R.id.advanced_search_toggle);
        advancedView = (LinearLayout) view.findViewById(R.id.linear_scroll_view);
        datePick = (Button) view.findViewById(R.id.date_pick);

        priceRange = (MultiSlider) view.findViewById(R.id.price_range);
        priceMin = (TextView) view.findViewById(R.id.price_min);
        priceMax = (TextView) view.findViewById(R.id.price_max);

        locationPick = (Button) view.findViewById(R.id.location_pick);

        date = (EditText) view.findViewById(R.id.date);

        setSearchModeListener();
        setDatePick();
        setRangeListener();
        setLocationPick();

        return view;
    }

    private void setSearchModeListener() {
        searchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    advancedView.setVisibility(View.VISIBLE);
                } else {
                    advancedView.setVisibility(View.GONE);
                }
            }
        });
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
            date.setText((day + "/" + (month + 1) + "/" + year).toString());
        }
    }

    private void setRangeListener() {
        priceRange.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                switch (thumbIndex) {
                    case 0:
                        priceMin.setText(value + " ");
                        break;
                    case 1:
                        priceMax.setText(value + " ");
                        break;
                    default:
                        break;
                }
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
    }
}



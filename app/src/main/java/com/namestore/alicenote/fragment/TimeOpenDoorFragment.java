package com.namestore.alicenote.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.activity.FirstSetupAcitivity;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;

/**
 * Created by kienht on 10/25/16.
 */

public class TimeOpenDoorFragment extends CoreFragment {
    Button mButtonBack;
    Button mButtonNext;
    TextView mTextViewTitle;
    CheckBox mCheckBoxMonday;
    CheckBox mCheckBoxTuesday;
    CheckBox mCheckBoxWednesday;
    CheckBox mCheckBoxThursday;
    CheckBox mCheckBoxFriday;
    CheckBox mCheckBoxSaturday;
    CheckBox mCheckBoxSunday;
    Spinner mSpinnerTimeStartDay1, mSpinnerTimeEndDay1;
    Spinner mSpinnerTimeStartDay2, mSpinnerTimeEndDay2;
    Spinner mSpinnerTimeStartDay3, mSpinnerTimeEndDay3;
    Spinner mSpinnerTimeStartDay4, mSpinnerTimeEndDay4;
    Spinner mSpinnerTimeStartDay5, mSpinnerTimeEndDay5;
    Spinner mSpinnerTimeStartDay6, mSpinnerTimeEndDay6;
    Spinner mSpinnerTimeStartDay7, mSpinnerTimeEndDay7;
    private FirstSetupAcitivity firstSetupAcitivity;
    OnFragmentInteractionListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_timeopendoor, container, false);
        initViews(view);
        initModels();
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setupSpinner() {
        mCheckBoxMonday.setText("Monday");
        mCheckBoxTuesday.setText("Tuesday");
        mCheckBoxWednesday.setText("Wednesday");
        mCheckBoxThursday.setText("Thursday");
        mCheckBoxFriday.setText("Friday");
        mCheckBoxSaturday.setText("Saturday");
        mCheckBoxSunday.setText("Sunday");
    }

    public void configSpinner() {
        String[] hour_open = getResources().getStringArray(R.array.hour_open);

        configSpinner(hour_open, mSpinnerTimeEndDay1, mSpinnerTimeEndDay2, mSpinnerTimeEndDay3, mSpinnerTimeEndDay4, mSpinnerTimeEndDay5,
                mSpinnerTimeEndDay6, mSpinnerTimeEndDay7, mSpinnerTimeStartDay1, mSpinnerTimeStartDay2, mSpinnerTimeStartDay3, mSpinnerTimeStartDay4,
                mSpinnerTimeStartDay5, mSpinnerTimeStartDay6, mSpinnerTimeStartDay7);
    }

    @Override
    protected void initViews(View view) {
        mTextViewTitle = (TextView) view.findViewById(R.id.tile_time_open).findViewById(R.id.title_first_setup);

        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);

        mCheckBoxMonday = (CheckBox) view.findViewById(R.id.day1).findViewById(R.id.days);
        mCheckBoxTuesday = (CheckBox) view.findViewById(R.id.day2).findViewById(R.id.days);
        mCheckBoxWednesday = (CheckBox) view.findViewById(R.id.day3).findViewById(R.id.days);
        mCheckBoxThursday = (CheckBox) view.findViewById(R.id.day4).findViewById(R.id.days);
        mCheckBoxFriday = (CheckBox) view.findViewById(R.id.day5).findViewById(R.id.days);
        mCheckBoxSaturday = (CheckBox) view.findViewById(R.id.day6).findViewById(R.id.days);
        mCheckBoxSunday = (CheckBox) view.findViewById(R.id.day7).findViewById(R.id.days);

        mSpinnerTimeStartDay1 = (Spinner) view.findViewById(R.id.day1).findViewById(R.id.hour_start);
        mSpinnerTimeEndDay1 = (Spinner) view.findViewById(R.id.day1).findViewById(R.id.hour_end);

        mSpinnerTimeStartDay2 = (Spinner) view.findViewById(R.id.day2).findViewById(R.id.hour_start);
        mSpinnerTimeEndDay2 = (Spinner) view.findViewById(R.id.day2).findViewById(R.id.hour_end);

        mSpinnerTimeStartDay3 = (Spinner) view.findViewById(R.id.day3).findViewById(R.id.hour_start);
        mSpinnerTimeEndDay3 = (Spinner) view.findViewById(R.id.day3).findViewById(R.id.hour_end);

        mSpinnerTimeStartDay4 = (Spinner) view.findViewById(R.id.day4).findViewById(R.id.hour_start);
        mSpinnerTimeEndDay4 = (Spinner) view.findViewById(R.id.day4).findViewById(R.id.hour_end);

        mSpinnerTimeStartDay5 = (Spinner) view.findViewById(R.id.day5).findViewById(R.id.hour_start);
        mSpinnerTimeEndDay5 = (Spinner) view.findViewById(R.id.day5).findViewById(R.id.hour_end);

        mSpinnerTimeStartDay6 = (Spinner) view.findViewById(R.id.day6).findViewById(R.id.hour_start);
        mSpinnerTimeEndDay6 = (Spinner) view.findViewById(R.id.day6).findViewById(R.id.hour_end);

        mSpinnerTimeStartDay7 = (Spinner) view.findViewById(R.id.day7).findViewById(R.id.hour_start);
        mSpinnerTimeEndDay7 = (Spinner) view.findViewById(R.id.day7).findViewById(R.id.hour_end);
    }

    @Override
    protected void initModels() {
        mTextViewTitle.setText("When is \"Your Salon\" Open?");
        mButtonBack.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        setupSpinner();
        configSpinner();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) context;
        }
        try {
            listener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) activity;
        }

        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                listener.onViewClick(Constants.SETUP_INFO_SALON);
                break;
            case R.id.button_next:
                listener.onViewClick(Constants.PICK_SERVICE);
                break;
            default:
                break;
        }
        super.onClick(view);
    }
}

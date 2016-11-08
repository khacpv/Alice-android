package com.namestore.alicenote.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

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
    Spinner mSpinnerTimeStartMonday, mSpinnerTimeEndMonday;
    Spinner mSpinnerTimeStartTuesday, mSpinnerTimeEndTuesday;
    Spinner mSpinnerTimeStartWednesday, mSpinnerTimeEndWednesday;
    Spinner mSpinnerTimeStartThursday, mSpinnerTimeEndThursday;
    Spinner mSpinnerTimeStartFriday, mSpinnerTimeEndFriday;
    Spinner mSpinnerTimeStartSaturday, mSpinnerTimeEndSaturday;
    Spinner mSpinnerTimeStartSunday, mSpinnerTimeEndSunday;

    class WorkingDay {
        String day;
        int startTime;
        int endTime;
        boolean checked = false;
    }

    List<WorkingDay> workingDays = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_timeopendoor, container, false);
        initViews(view);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (int i = 2; i <= 8; i++) {
            WorkingDay item = new WorkingDay();
            item.day = "Thu " + i;
            item.startTime = 0;
            item.endTime = 0;
            workingDays.add(item);
        }

        initModels();
    }

    @Override
    protected void initModels() {
        mTextViewTitle.setText("When is \"Your Salon\" Open?");
        mButtonBack.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        setupSpinner();
        configSpinner();
    }

    public void setupSpinner() {
        // thu 2
        mCheckBoxMonday.setChecked(workingDays.get(0).checked);
        mSpinnerTimeStartMonday.setSelection(workingDays.get(0).startTime);
        mSpinnerTimeEndMonday.setSelection(workingDays.get(0).endTime);
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

        ViewUtils.configSpinner(getActivity(), hour_open, mSpinnerTimeEndMonday, mSpinnerTimeEndTuesday, mSpinnerTimeEndWednesday, mSpinnerTimeEndThursday, mSpinnerTimeEndFriday,
                mSpinnerTimeEndSaturday, mSpinnerTimeEndSunday, mSpinnerTimeStartMonday, mSpinnerTimeStartTuesday, mSpinnerTimeStartWednesday, mSpinnerTimeStartThursday,
                mSpinnerTimeStartFriday, mSpinnerTimeStartSaturday, mSpinnerTimeStartSunday);
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

        mSpinnerTimeStartMonday = (Spinner) view.findViewById(R.id.day1).findViewById(R.id.hour_start);
        mSpinnerTimeEndMonday = (Spinner) view.findViewById(R.id.day1).findViewById(R.id.hour_end);

        mSpinnerTimeStartTuesday = (Spinner) view.findViewById(R.id.day2).findViewById(R.id.hour_start);
        mSpinnerTimeEndTuesday = (Spinner) view.findViewById(R.id.day2).findViewById(R.id.hour_end);

        mSpinnerTimeStartWednesday = (Spinner) view.findViewById(R.id.day3).findViewById(R.id.hour_start);
        mSpinnerTimeEndWednesday = (Spinner) view.findViewById(R.id.day3).findViewById(R.id.hour_end);

        mSpinnerTimeStartThursday = (Spinner) view.findViewById(R.id.day4).findViewById(R.id.hour_start);
        mSpinnerTimeEndThursday = (Spinner) view.findViewById(R.id.day4).findViewById(R.id.hour_end);

        mSpinnerTimeStartFriday = (Spinner) view.findViewById(R.id.day5).findViewById(R.id.hour_start);
        mSpinnerTimeEndFriday = (Spinner) view.findViewById(R.id.day5).findViewById(R.id.hour_end);

        mSpinnerTimeStartSaturday = (Spinner) view.findViewById(R.id.day6).findViewById(R.id.hour_start);
        mSpinnerTimeEndSaturday = (Spinner) view.findViewById(R.id.day6).findViewById(R.id.hour_end);

        mSpinnerTimeStartSunday = (Spinner) view.findViewById(R.id.day7).findViewById(R.id.hour_start);
        mSpinnerTimeEndSunday = (Spinner) view.findViewById(R.id.day7).findViewById(R.id.hour_end);

        initEvent();
    }

    private void initEvent() {
        mCheckBoxMonday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                workingDays.get(0).checked = b;
            }
        });

        mSpinnerTimeStartMonday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                workingDays.get(0).startTime = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerTimeEndMonday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                workingDays.get(0).endTime = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showSetupInfoSalon();
                }
                break;
            case R.id.button_next:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).pickSalonService();
                }
                break;
            default:
                break;
        }
        super.onClick(view);
    }
}

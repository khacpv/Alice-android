package com.namestore.alicenote.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.activity.FirstSetupAcitivity;
import com.namestore.alicenote.activity.StartActivity;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;
import com.namestore.alicenote.utils.ViewUtils;

/**
 * Created by kienht on 10/31/16.
 */

public class SetupInfoSalonFragment extends CoreFragment {

    Button mButtonBack;
    Button mButtonNext;
    TextView mTextViewTitle;
    EditText mEditTexBsnName;
    EditText mEditTexBsnCity;
    EditText mEditTexBsnPostCode;
    EditText mEditTexBsnAddress;
    Spinner mSpinnerBsnState;
    Spinner mSpinnerBsnType;
    LinearLayout linearLayout;
    private FirstSetupAcitivity firstSetupAcitivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_setup_info_salon, container, false);
        initViews(view);
        initModels();
        return view;

    }

    public void configSpinner() {

        String[] bussiness_type = getResources().getStringArray(R.array.bussiness_type);
        String[] bussiness_state = getResources().getStringArray(R.array.us_states);

        ViewUtils.configSpinner(getActivity(), bussiness_type, mSpinnerBsnType);
        ViewUtils.configSpinner(getActivity(), bussiness_state, mSpinnerBsnState);

    }

    @Override
    protected void initViews(View view) {
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);

        mTextViewTitle = (TextView) view.findViewById(R.id.tile_profile_setup).findViewById(R.id.title_first_setup);


        mEditTexBsnName = (EditText) view.findViewById(R.id.editText_bussiness_name);
        mEditTexBsnCity = (EditText) view.findViewById(R.id.editText_bussiness_city);
        mEditTexBsnAddress = (EditText) view.findViewById(R.id.editText_bussiness_address);
        mEditTexBsnPostCode = (EditText) view.findViewById(R.id.editText_bussiness_postcode);

        mSpinnerBsnState = (Spinner) view.findViewById(R.id.spinner_bussiness_state);
        mSpinnerBsnType = (Spinner) view.findViewById(R.id.spinner_bussiness_type);

        linearLayout = (LinearLayout) view.findViewById(R.id.frgment_setup_info_salon);

    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);
        mButtonBack.setVisibility(View.INVISIBLE);
        mButtonNext.setOnClickListener(this);
        mTextViewTitle.setText("Welcome to AliceNote");
        ViewUtils.configEditTex(getActivity(), mEditTexBsnName, linearLayout, "Bussiness Name", 0, null);
        ViewUtils.configEditTex(getActivity(), mEditTexBsnCity, linearLayout, "City", 0, null);
        ViewUtils.configEditTex(getActivity(), mEditTexBsnPostCode, linearLayout, "Post Code", 0, null);
        ViewUtils.configEditTex(getActivity(), mEditTexBsnAddress, linearLayout, "Address", 0, null);

        configSpinner();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof StartActivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof StartActivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) activity;
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_next:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showTimeOpenDoorSalon();
                }
                break;
        }
    }
}

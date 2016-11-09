package com.namestore.alicenote.fragment.firstsetup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.models.FirstSetup;
import com.namestore.alicenote.utils.AppUtils;
import com.namestore.alicenote.utils.ViewUtils;

/**
 * Created by kienht on 10/31/16.
 */

public class ShopRegisterFragment extends CoreFragment {

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
    FirstSetup firstSetup = new FirstSetup();
    AppUtils appUtils;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_shop_register, container, false);
        initViews(view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModels();
        appUtils = new AppUtils(firstSetupAcitivity);
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
        ViewUtils.configEditText(getActivity(), mEditTexBsnName, linearLayout, "Bussiness Name", 0, null);
        ViewUtils.configEditText(getActivity(), mEditTexBsnCity, linearLayout, "City", 0, null);
        ViewUtils.configEditText(getActivity(), mEditTexBsnPostCode, linearLayout, "Post Code", 0, null);
        ViewUtils.configEditText(getActivity(), mEditTexBsnAddress, linearLayout, "Address", 0, null);

        configSpinner();
    }


    public void configSpinner() {
        String[] bussiness_type = getResources().getStringArray(R.array.bussiness_type);
        String[] bussiness_state = getResources().getStringArray(R.array.us_states);

        ViewUtils.configSpinner(getActivity(), bussiness_type, mSpinnerBsnType);
        ViewUtils.configSpinner(getActivity(), bussiness_state, mSpinnerBsnState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) activity;
        }
    }

    public boolean checkEmpty(String... strings) {
        boolean isEmpty = false;
        for (String string : strings) {
            if ((TextUtils.isEmpty(string))) {
                isEmpty = true;
                return isEmpty;
            }
        }
        return isEmpty;
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_next:
                if (firstSetupAcitivity != null) {
                    firstSetup = firstSetupAcitivity.getFirstSetup();
                    firstSetup.bussinessName = mEditTexBsnName.getText().toString();
                    firstSetup.bussinessType = mSpinnerBsnType.getSelectedItem().toString();
                    firstSetup.state = mSpinnerBsnState.getSelectedItem().toString();
                    firstSetup.city = mEditTexBsnCity.getText().toString();
                    firstSetup.postCode = mEditTexBsnPostCode.getText().toString();
                    firstSetup.address = mEditTexBsnAddress.getText().toString();

                    if (checkEmpty(firstSetup.bussinessName, firstSetup.bussinessType,
                            firstSetup.state, firstSetup.city, firstSetup.postCode, firstSetup.address)) {
                        appUtils.showNoticeDialog("Please filling in the blanks");
                    } else {
                        if (mActivity instanceof OnFirstSetupActivityListener) {
                            ((OnFirstSetupActivityListener) mActivity).showTimeOpenDoorSalon();
                        }
                    }
                }

                break;
        }
    }
}

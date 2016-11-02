package com.namestore.alicenote.activity;

import com.namestore.alicenote.data.Constants;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.namestore.alicenote.R;
import com.namestore.alicenote.core.CoreActivity;
import com.namestore.alicenote.fragment.ConfigSalonServicesFragment;
import com.namestore.alicenote.fragment.HairServicesFragment;
import com.namestore.alicenote.fragment.NailServicesFragment;
import com.namestore.alicenote.fragment.PickSalonServiceFragment;
import com.namestore.alicenote.fragment.SetupInfoSalonFragment;
import com.namestore.alicenote.fragment.TimeOpenDoorFragment;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;

/**
 * Created by kienht on 10/25/16.
 */

public class FirstSetupAcitivity extends CoreActivity implements View.OnClickListener, OnFragmentInteractionListener {


    private SetupInfoSalonFragment mSetupInfoSalonFragment;
    private TimeOpenDoorFragment mTimeOpenDoorFragment;
    private PickSalonServiceFragment mPickSalonServiceFragment;
    private HairServicesFragment mHairServicesFragment;
    private NailServicesFragment mNailServicesFragment;
    private ConfigSalonServicesFragment mConfigSalonServicesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_firstsetup);
        if (!getIntent().getExtras().containsKey(Constants.FIRST_SETUP_SCREEN)) {
            Log.e("TAG", "bạn phải truyền key FIRST_SETUP_SCREEN sang màn hình này");
            finish();
            return;
        }

        mSetupInfoSalonFragment = new SetupInfoSalonFragment();
        mTimeOpenDoorFragment = new TimeOpenDoorFragment();
        mPickSalonServiceFragment = new PickSalonServiceFragment();
        mHairServicesFragment = new HairServicesFragment();
        mNailServicesFragment = new NailServicesFragment();
        mConfigSalonServicesFragment = new ConfigSalonServicesFragment();

        if (getIntent().getExtras().getInt(Constants.FIRST_SETUP_SCREEN) == Constants.KEY_SETUP_INFO_SALON) {
            showSetupInfoSalon();
        }
    }


    public void showSetupInfoSalon() {
        getFragmentManager().beginTransaction().replace(R.id.container, mSetupInfoSalonFragment).commit();

    }

    public void showTimeOpenDoorSalon() {
        getFragmentManager().beginTransaction().replace(R.id.container, mTimeOpenDoorFragment).commit();

    }

    public void pickSalonService() {
        getFragmentManager().beginTransaction().replace(R.id.container, mPickSalonServiceFragment).commit();

    }

    public void nailService() {
        getFragmentManager().beginTransaction().replace(R.id.container, mNailServicesFragment).commit();

    }

    public void hairService() {
        getFragmentManager().beginTransaction().replace(R.id.container, mHairServicesFragment).commit();

    }

    public void configServices() {
        getFragmentManager().beginTransaction().replace(R.id.container, mConfigSalonServicesFragment).commit();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onViewClick(String tag) {
        switch (tag) {
            case Constants.SETUP_INFO_SALON:
                showSetupInfoSalon();
                break;
            case Constants.TIME_OPEN_DOOR_FRAGMENT:
                showTimeOpenDoorSalon();
                break;
            case Constants.PICK_SERVICE:
                pickSalonService();
                break;
            case Constants.CONFIG_SERVICE:
                configServices();
                break;
            case Constants.NAIL_SERVICE:
                nailService();
                break;
            case Constants.HAIR_SERVICE:
                hairService();
                break;
        }
    }

    @Override
    public void onViewClick(String tag, Object object) {
        switch (tag) {
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            default:
                break;
        }

    }
}

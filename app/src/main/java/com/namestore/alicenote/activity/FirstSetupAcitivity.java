package com.namestore.alicenote.activity;

import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import com.namestore.alicenote.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;

import java.util.ArrayList;

/**
 * Created by kienht on 10/25/16.
 */


public class FirstSetupAcitivity extends CoreActivity implements View.OnClickListener,
        OnFragmentInteractionListener,
        OnFirstSetupActivityListener {


    private SetupInfoSalonFragment mSetupInfoSalonFragment;
    private TimeOpenDoorFragment mTimeOpenDoorFragment;
    private PickSalonServiceFragment mPickSalonServiceFragment;
    private HairServicesFragment mHairServicesFragment;
    private NailServicesFragment mNailServicesFragment;
    private ConfigSalonServicesFragment mConfigSalonServicesFragment;

    ArrayList<CoreFragment> fragments = new ArrayList<>();

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

        fragments.add(mSetupInfoSalonFragment);
        fragments.add(mTimeOpenDoorFragment);
        fragments.add(mPickSalonServiceFragment);
        fragments.add(mHairServicesFragment);
        fragments.add(mNailServicesFragment);
        fragments.add(mConfigSalonServicesFragment);

        getFragmentManager().beginTransaction()
                .add(R.id.container, mSetupInfoSalonFragment)
                .add(R.id.container, mTimeOpenDoorFragment)
                .add(R.id.container, mPickSalonServiceFragment)
                .add(R.id.container, mHairServicesFragment)
                .add(R.id.container, mNailServicesFragment)
                .add(R.id.container, mConfigSalonServicesFragment)
                .commit();

        if (getIntent().getExtras().getInt(Constants.FIRST_SETUP_SCREEN) == Constants.KEY_SETUP_INFO_SALON) {
            showFragment(mSetupInfoSalonFragment);
        }
    }

    public void showFragment(Fragment fragmentToShow) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        for (CoreFragment _fragment : fragments) {
            if (_fragment == fragmentToShow) {
                transaction.show(fragmentToShow);
            } else {
                transaction.hide(_fragment);
            }
        }
        transaction.commit();
    }


    @Override
    public void showSetupInfoSalon() {
        showFragment(mSetupInfoSalonFragment);

    }

    @Override
    public void showTimeOpenDoorSalon() {
        showFragment(mTimeOpenDoorFragment);

    }

    @Override
    public void pickSalonService() {
        showFragment(mPickSalonServiceFragment);

    }

    @Override
    public void nailService() {
        showFragment(mNailServicesFragment);

    }

    @Override
    public void hairService() {
        showFragment(mHairServicesFragment);

    }

    @Override
    public void configServices() {
        showFragment(mConfigSalonServicesFragment);

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onViewClick(String tag) {
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

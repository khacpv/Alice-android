package com.namestore.alicenote.activity;

import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.namestore.alicenote.R;
import com.namestore.alicenote.core.CoreActivity;
import com.namestore.alicenote.fragment.firstsetup.ServicesDetailFragment;
import com.namestore.alicenote.fragment.firstsetup.HairServicesFragment;
import com.namestore.alicenote.fragment.firstsetup.NailServicesFragment;
import com.namestore.alicenote.fragment.firstsetup.ShopServicesCategoryFragment;
import com.namestore.alicenote.fragment.firstsetup.ShopRegisterFragment;
import com.namestore.alicenote.fragment.firstsetup.ShopWorkingDayFragment;
import com.namestore.alicenote.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;
import com.namestore.alicenote.models.FirstSetup;

import java.util.ArrayList;

/**
 * Created by kienht on 10/25/16.
 */


public class FirstSetupAcitivity extends CoreActivity implements View.OnClickListener,
        OnFragmentInteractionListener,
        OnFirstSetupActivityListener {

    private ShopRegisterFragment mShopRegisterFragment;
    private ShopWorkingDayFragment mShopWorkingDayFragment;
    private ShopServicesCategoryFragment mShopServicesCategoryFragment;
    private HairServicesFragment mHairServicesFragment;
    private NailServicesFragment mNailServicesFragment;
    private ServicesDetailFragment mServicesDetailFragment;
    FirstSetup firstSetup = new FirstSetup();

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

        mShopRegisterFragment = new ShopRegisterFragment();
        mShopWorkingDayFragment = new ShopWorkingDayFragment();
        mShopServicesCategoryFragment = new ShopServicesCategoryFragment();
        mHairServicesFragment = new HairServicesFragment();
        mNailServicesFragment = new NailServicesFragment();
        mServicesDetailFragment = new ServicesDetailFragment();

        fragments.add(mShopRegisterFragment);
        fragments.add(mShopWorkingDayFragment);
        fragments.add(mShopServicesCategoryFragment);
        fragments.add(mHairServicesFragment);
        fragments.add(mNailServicesFragment);
        fragments.add(mServicesDetailFragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mShopRegisterFragment)
                .add(R.id.container, mShopWorkingDayFragment)
                .add(R.id.container, mShopServicesCategoryFragment)
                .add(R.id.container, mHairServicesFragment)
                .add(R.id.container, mNailServicesFragment)
                .add(R.id.container, mServicesDetailFragment)
                .commit();

        if (getIntent().getExtras().getInt(Constants.FIRST_SETUP_SCREEN) == Constants.KEY_SETUP_INFO_SALON) {
            showFragment(mShopRegisterFragment);
        }
    }

    public void showFragment(Fragment fragmentToShow) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
        showFragment(mShopRegisterFragment);


    }

    @Override
    public void showTimeOpenDoorSalon() {
        showFragment(mShopWorkingDayFragment);
    }

    @Override
    public void pickSalonService() {
        showFragment(mShopServicesCategoryFragment);
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
        showFragment(mServicesDetailFragment);
    }

    public FirstSetup getFirstSetup() {
        if (firstSetup == null) {
            firstSetup = new FirstSetup();
        }
        return firstSetup;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onViewClick(String tag) {
        switch (tag) {
            case Constants.DASHBOARD_SCREEN:
                Intent intent = new Intent(FirstSetupAcitivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
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

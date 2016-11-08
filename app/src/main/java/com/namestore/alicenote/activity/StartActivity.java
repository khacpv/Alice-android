package com.namestore.alicenote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.namestore.alicenote.R;
import com.namestore.alicenote.adapter.ViewPagerAdapter;
import com.namestore.alicenote.core.CoreActivity;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by kienht on 10/25/16.
 */

public class StartActivity extends CoreActivity implements OnFragmentInteractionListener {

    Button mButtonLogin;
    Button mButtonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        AutoScrollViewPager viewpager = (AutoScrollViewPager) findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        viewpager.setAdapter(new ViewPagerAdapter(3));
        indicator.setViewPager(viewpager);
        viewpager.setInterval(3000);
        viewpager.startAutoScroll();
        viewpager.setCurrentItem(0);

        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonSignup = (Button) findViewById(R.id.button_signup);

        mButtonLogin.setOnClickListener(this);
        mButtonSignup.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                moveLoginSignupAct(Constants.KEY_LOGIN);
                break;
            case R.id.button_signup:
                moveLoginSignupAct(Constants.KEY_SIGNUP);
                break;
        }

    }

    /**
     * Open LoginFragment or SignupFragment
     */
    public void moveLoginSignupAct(int key) {
        Intent mIntent = new Intent(StartActivity.this, LoginSignupActivity.class);
        mIntent.putExtra(Constants.LOGIN_SIGNUP_SCREEN, key);
        startActivity(mIntent);
    }

    @Override
    public void onViewClick(String tag) {

    }

    @Override
    public void onViewClick(String tag, Object object) {

    }

}

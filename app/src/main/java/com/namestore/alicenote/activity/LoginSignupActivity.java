package com.namestore.alicenote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.namestore.alicenote.R;
import com.namestore.alicenote.core.CoreActivity;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.fragment.LoginFragment;
import com.namestore.alicenote.fragment.SignUpFragment;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;

/**
 * Created by kienht on 10/25/16.
 */

public class LoginSignupActivity extends CoreActivity implements OnFragmentInteractionListener {


    private LoginFragment mLoginFragment;
    private SignUpFragment mSignUpFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_signup);

        if (!getIntent().getExtras().containsKey(Constants.LOGIN_SIGNUP_SCREEN)) {
            Log.e("TAG", "bạn phải truyền key LOGIN_SIGNUP_SCREEN sang màn hình này");
            finish();
            return;
        }

        mLoginFragment = new LoginFragment();
        mSignUpFragment = new SignUpFragment();

        if (getIntent().getExtras().getInt(Constants.LOGIN_SIGNUP_SCREEN) == Constants.KEY_LOGIN) {
            showLoginView();
        } else {
            showSignupView();
        }
    }

    /**
     * khi nào muốn hiện Login screen thì gọi hàm này. Có thể gọi từ SignUpFragment
     */
    private void showLoginView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mLoginFragment).commit();
    }

    /**
     * khi nào muốn hiện SignUp screen thì gọi hàm này. Có thể gọi từ LoginFragment
     */
    private void showSignupView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mSignUpFragment).commit();

    }

    @Override
    public void onViewClick(String tag) {
        switch (tag) {
            case Constants.SIGNUP_FRAGMENT:
                showSignupView();
                break;
            case Constants.LOGIN_FRAGMENT:
                showLoginView();
                break;
            case Constants.LOGIN_FACEBOOK:
                showShortToast(Constants.LOGIN_FACEBOOK);
                break;
            case Constants.LOGIN_GOOGLE:
                showShortToast(Constants.LOGIN_GOOGLE);
                break;
            case Constants.LOGIN_BUTTON:
                moveFirstSetupAct(Constants.KEY_SETUP_INFO_SALON);
                break;
            case Constants.SIGNUP_BUTTON:
                moveFirstSetupAct(Constants.KEY_SETUP_INFO_SALON);
                break;
        }
    }

    public void moveFirstSetupAct(int key) {
        Intent mIntent = new Intent(LoginSignupActivity.this, FirstSetupAcitivity.class);
        mIntent.putExtra(Constants.FIRST_SETUP_SCREEN, key);
        startActivity(mIntent);
    }


    @Override
    public void onViewClick(String tag, Object object) {

    }

    @Override
    public void onClick(View view) {

    }
}

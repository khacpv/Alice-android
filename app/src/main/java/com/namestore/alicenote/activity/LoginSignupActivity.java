package com.namestore.alicenote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.namestore.alicenote.R;
import com.namestore.alicenote.connect.ServiceGenerator;
import com.namestore.alicenote.connect.network.api.AliceApi;
import com.namestore.alicenote.connect.reponse.RSPLogin;
import com.namestore.alicenote.connect.reponse.RSPSignup;
import com.namestore.alicenote.core.CoreActivity;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.fragment.LoginFragment;
import com.namestore.alicenote.fragment.SignUpFragment;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;
import com.namestore.alicenote.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kienht on 10/25/16.
 */

public class LoginSignupActivity extends CoreActivity implements OnFragmentInteractionListener {


    private LoginFragment mLoginFragment;
    private SignUpFragment mSignUpFragment;
    AliceApi aliceApi;


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

        aliceApi = ServiceGenerator.creatService(AliceApi.class);
    }

    /**
     * khi nào muốn hiện Login screen thì gọi hàm này. Có thể gọi từ SignUpFragment
     */
    private void showLoginView() {
        getFragmentManager().beginTransaction().replace(R.id.container, mLoginFragment).commit();
    }

    /**
     * khi nào muốn hiện SignUp screen thì gọi hàm này. Có thể gọi từ LoginFragment
     */
    private void showSignupView() {
        getFragmentManager().beginTransaction().replace(R.id.container, mSignUpFragment).commit();

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
//            case Constants.LOGIN_BUTTON:
//                 moveFirstSetupAct(Constants.KEY_SETUP_INFO_SALON);
//                break;
//            case Constants.SIGNUP_BUTTON:
//                 moveFirstSetupAct(Constants.KEY_SETUP_INFO_SALON);
//                break;
        }
    }

    public void moveFirstSetupAct(int key) {
        Intent mIntent = new Intent(LoginSignupActivity.this, FirstSetupAcitivity.class);
        mIntent.putExtra(Constants.FIRST_SETUP_SCREEN, key);
        startActivity(mIntent);
    }


    @Override
    public void onViewClick(String tag, Object object) {
        User user;

        switch (tag) {

            case Constants.LOGIN_BUTTON:
                user = (User) object;
                aliceApi.login(user).enqueue(new Callback<RSPLogin>() {
                    @Override
                    public void onResponse(Call<RSPLogin> call, Response<RSPLogin> response) {
                        logE("OK || STATUS" + response.body().getStatus());
                    }

                    @Override
                    public void onFailure(Call<RSPLogin> call, Throwable t) {
                        logE("FAILED " + t.getLocalizedMessage());
                    }
                });
                break;

            case Constants.SIGNUP_BUTTON:
                user = (User) object;
                aliceApi.signup(user).enqueue(new Callback<RSPSignup>() {
                    @Override
                    public void onResponse(Call<RSPSignup> call, Response<RSPSignup> response) {
                        logE("OK || STATUS" + response.body().getStatus());
                    }

                    @Override
                    public void onFailure(Call<RSPSignup> call, Throwable t) {
                        logE("FAILED " + t.getLocalizedMessage());
                    }
                });

                break;

        }

    }

    @Override
    public void onClick(View view) {

    }
}

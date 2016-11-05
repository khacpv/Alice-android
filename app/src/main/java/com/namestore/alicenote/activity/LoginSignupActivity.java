package com.namestore.alicenote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kienht on 10/25/16.
 */

public class LoginSignupActivity extends CoreActivity implements OnFragmentInteractionListener {

    private AccessTokenTracker mAccessTokenTracker;
    private CallbackManager mCallbackManagerFb;
    AccessToken mAccessToken;

    private LoginFragment mLoginFragment;
    private SignUpFragment mSignUpFragment;
    AliceApi aliceApi;
    User mUser = new User();
    private LoginButton mLoginButtonFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {
                mAccessToken = AccessToken.getCurrentAccessToken();
            }
        });

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

        mLoginButtonFb = (LoginButton) findViewById(R.id.button_facebook_login);
        loginFb();
    }

    /**
     * @login via Facebook
     */
    public void loginFb() {
        mCallbackManagerFb = CallbackManager.Factory.create();
        mLoginButtonFb.setVisibility(View.GONE);
        mLoginButtonFb.setReadPermissions(Arrays.asList("public_profile", "email"));
        mLoginButtonFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginButtonFb.setClickable(false);
                mAccessTokenTracker = new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                        AccessToken mAccessToken = newToken;
                    }
                };
                FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
                    @Override
                    public void onInitialized() {
                        mLoginButtonFb.registerCallback(mCallbackManagerFb, new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                //AccessToken mAccessToken = loginResult.getAccessToken();
                                // PrefUtils.getInstance(LoginScreen.this).set(PrefUtils.KEY_ACCESS_TOKEN_FB, mAccessToken.getToken());
                                getUserInfoFromFb();

                            }

                            @Override
                            public void onCancel() {
                                logE("FB CANCEL");
                                mLoginButtonFb.setClickable(true);
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                logE("FB ERROR");
                                mLoginButtonFb.setClickable(true);

                            }
                        });
                    }
                });

                mAccessTokenTracker.startTracking();
            }
        });

    }

    /**
     * @get user info after login fb success
     */
    private void getUserInfoFromFb() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(
                    JSONObject object, GraphResponse response) {
                try {
                    mUser.email = object.getString("email");
                    mUser.first_name = object.getString("first_name");
                    mUser.last_name = object.getString("last_name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,gender,name,first_name,last_name,email,birthday,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
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


    public void moveFirstSetupAct(int key) {
        Intent mIntent = new Intent(LoginSignupActivity.this, FirstSetupAcitivity.class);
        mIntent.putExtra(Constants.FIRST_SETUP_SCREEN, key);
        startActivity(mIntent);
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
                mLoginButtonFb.performClick();
                break;
            case Constants.LOGIN_GOOGLE:
                showShortToast(Constants.LOGIN_GOOGLE);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManagerFb.onActivityResult(requestCode, resultCode, data);
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

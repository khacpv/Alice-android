package com.namestore.alicenote.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.namestore.alicenote.R;
import com.namestore.alicenote.connect.ServiceGenerator;
import com.namestore.alicenote.connect.network.api.AliceApi;
import com.namestore.alicenote.connect.reponse.RSPLoginSignup;
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

public class LoginSignupActivity extends CoreActivity implements OnFragmentInteractionListener, GoogleApiClient.OnConnectionFailedListener {

    public final static int LOGIN_ERROR = 0;
    public final static int LOGIN_SUCCESS = 1;
    public final static int LOGED = 2;
    public final static int FIRST_LOGIN = 3;
    private static final int RC_SIGN_IN = 9001;

    private AccessTokenTracker mAccessTokenTracker;
    private CallbackManager mCallbackManagerFb;
    AccessToken mAccessToken;
    private LoginFragment mLoginFragment;
    private SignUpFragment mSignUpFragment;
    AliceApi aliceApi;
    User mUser = new User();
    private LoginButton mLoginButtonFb;
    private SignInButton mSignInButoonGoogle;
    private ProgressDialog prgDialog;
    private GoogleApiClient mGoogleApiClient;

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

        prgDialog = new ProgressDialog(this);

        loginGooglePlus();

        mSignInButoonGoogle = (SignInButton) findViewById(R.id.button_google_login);
        mSignInButoonGoogle.setVisibility(View.GONE);

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
                    mUser.id = object.getString("id");
                    mUser.email = object.getString("email");
                    mUser.first_name = object.getString("first_name");
                    mUser.last_name = object.getString("last_name");
                    String gender = object.getString("gender");
                    if (gender.equals("male")) {
                        mUser.gender = SignUpFragment.GENDER_MALE;
                    } else {
                        mUser.gender = SignUpFragment.GENDER_FEMALE;
                    }
                    mUser.password_hash = "";
                    mUser.telephone = 0;
                    setPrgDialog("Loging");

                    aliceApi.loginFb(mUser).enqueue(new Callback<RSPLoginSignup>() {
                        @Override
                        public void onResponse(Call<RSPLoginSignup> call, Response<RSPLoginSignup> response) {
                            prgDialog.hide();
                            logE("" + response.body().getStatus());
                        }

                        @Override
                        public void onFailure(Call<RSPLoginSignup> call, Throwable t) {

                        }
                    });
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

    public void loginGooglePlus() {
        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
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

    public void setPrgDialog(String text) {
        prgDialog.setMessage(text);
        prgDialog.show();
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
                mSignInButoonGoogle.performClick();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from Facebook
        mCallbackManagerFb.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.
                GoogleSignInAccount acct = result.getSignInAccount();

                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                //Uri personPhoto = acct.getPhotoUrl();

                logE(personEmail + "||" + personFamilyName + "||" + personGivenName + "||" + personName + "||" + personId);

            } else {
                // Signed out, show unauthenticated UI.
            }
        }
    }

    @Override
    public void onViewClick(String tag, Object object) {
        switch (tag) {
            case Constants.LOGIN_BUTTON:

                hideKeyBoard();
                setPrgDialog("Loging");
                mUser = (User) object;
                logE("REQUEST: " + aliceApi.login(mUser).request().body().contentType().toString());
                aliceApi.login(mUser).enqueue(new Callback<RSPLoginSignup>() {
                    @Override
                    public void onResponse(Call<RSPLoginSignup> call, Response<RSPLoginSignup> response) {
                        if (response.isSuccessful()) {

                            prgDialog.hide();

                            switch (response.body().getStatus()) {
                                case LOGIN_ERROR:
                                    if (response.body().getErrors().has("email")) {
                                        String emailBlank = response.body().getErrors().get("email").getAsString();
                                        String passwordBlank = response.body().getErrors().get("password_hash").getAsString();
                                        if (mLoginFragment != null) {
                                            mLoginFragment.setHintEdittex(emailBlank, passwordBlank);
                                        }
                                    } else {
                                        String incorrect = response.body().getErrors().get("password_hash").getAsString();
                                        if (mLoginFragment != null) {
                                            mLoginFragment.setTextViewIncorrect(incorrect);
                                        }
                                    }
                                    break;
                                case LOGIN_SUCCESS:
                                    // moveFirstSetupAct(Constants.KEY_SETUP_INFO_SALON);
                                    break;
                                case LOGED:
                                    // moveFirstSetupAct(Constants.KEY_SETUP_INFO_SALON);
                                    break;
                                case FIRST_LOGIN:
                                    // moveFirstSetupAct(Constants.KEY_SETUP_INFO_SALON);
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RSPLoginSignup> call, Throwable t) {
                        if (call.isCanceled()) {
                            logE("request was cancelled");
                        } else {
                            logE("FAILED " + t.getLocalizedMessage());
                        }

                    }
                });
                break;

            case Constants.SIGNUP_BUTTON:
                hideKeyBoard();
                setPrgDialog("Registering");
                mUser = (User) object;
                aliceApi.signup(mUser).enqueue(new Callback<RSPLoginSignup>() {
                    @Override
                    public void onResponse(Call<RSPLoginSignup> call, Response<RSPLoginSignup> response) {
                        logE("OK || STATUS" + response.body().getStatus());
                        prgDialog.hide();
                    }

                    @Override
                    public void onFailure(Call<RSPLoginSignup> call, Throwable t) {
                        if (call.isCanceled()) {
                            logE("request was cancelled");
                        } else {
                            logE("FAILED " + t.getLocalizedMessage());
                        }
                    }
                });

                break;

        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        if (prgDialog != null) {
            prgDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

package com.namestore.alicenote.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.activity.StartActivity;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;
import com.namestore.alicenote.models.User;

/**
 * Created by kienht on 10/24/16.
 */

public class LoginFragment extends CoreFragment {

    TextView mTextViewSignup;
    Button mButtonLogin;
    EditText mEditTexEmail;
    EditText mEditTexPassword;
    TextView mTextViewForgotPass;
    TextView mTextViewReportError;
    Button mButtonFb;
    Button mButtonGoogleP;
    TextView mTextViewContact;
    SwitchCompat switchCompatLogin;
    private StartActivity startActivity;
    OnFragmentInteractionListener listener;
    LinearLayout linearLayout;
    User mUser = new User();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_login, container, false);
        initViews(view);
        initModels();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof StartActivity) {
            this.startActivity = (StartActivity) context;
        }

        try {
            listener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof StartActivity) {
            this.startActivity = (StartActivity) activity;
        }

        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }


    @Override
    protected void initViews(View view) {
        mTextViewSignup = (TextView) view.findViewById(R.id.textview_signup);
        mButtonLogin = (Button) view.findViewById(R.id.button_login);
        mEditTexEmail = (EditText) view.findViewById(R.id.editText_login_username);
        mEditTexPassword = (EditText) view.findViewById(R.id.editText_login_password);
        mTextViewForgotPass = (TextView) view.findViewById(R.id.textview_forgot_pass);
        mTextViewReportError = (TextView) view.findViewById(R.id.textview_report_error_login);
        mButtonFb = (Button) view.findViewById(R.id.button_facebook_login);
        mButtonGoogleP = (Button) view.findViewById(R.id.button_google_login);
        mTextViewContact = (TextView) view.findViewById(R.id.textview_contact);
        switchCompatLogin = (SwitchCompat) view.findViewById(R.id.switch_compat_login);
        linearLayout = (LinearLayout) view.findViewById(R.id.frgment_login);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);
        mTextViewSignup.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mTextViewForgotPass.setOnClickListener(this);
        mTextViewReportError.setOnClickListener(this);
        mButtonFb.setOnClickListener(this);
        mButtonGoogleP.setOnClickListener(this);
        mTextViewContact.setOnClickListener(this);
        configEditTex(mEditTexEmail, linearLayout, "Email", R.drawable.icon_email);
        configEditTex(mEditTexPassword, linearLayout, "Password", R.drawable.icon_password);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_login:
                mUser.email = mEditTexEmail.getText().toString();
                mUser.password_hash = mEditTexPassword.getText().toString();

                listener.onViewClick(Constants.LOGIN_BUTTON, mUser);
                break;

            case R.id.textview_forgot_pass:
                showShortToast(Constants.FORGOT_PASS);
                break;

            case R.id.textview_report_error_login:
                showShortToast(Constants.REPORT_ERROR);
                break;

            case R.id.button_facebook_login:
                listener.onViewClick(Constants.LOGIN_FACEBOOK);
                break;

            case R.id.button_google_login:
                listener.onViewClick(Constants.LOGIN_GOOGLE);
                break;

            case R.id.textview_contact:
                showShortToast(Constants.CONTACT_ALICE);
                break;

            case R.id.textview_signup:
                listener.onViewClick(Constants.SIGNUP_FRAGMENT);
                break;

            default:
                break;
        }
    }
}

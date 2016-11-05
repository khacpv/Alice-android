package com.namestore.alicenote.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

public class SignUpFragment extends CoreFragment {
    EditText mEditTextEmail;
    EditText mEditTextPassword;
    EditText mEditTextPhone;
    EditText mEditTextFirstName;
    EditText mEditTextLastName;
    Spinner mSpinnerGender;
    Button mButtonSignup;
    TextView mTextViewAlreadyAccount;
    TextView mTextViewReport;
    Button mButtonFb;
    Button mButtonGoogle;
    LinearLayout linearLayout;
    private StartActivity loginActivity;
    User mUser = new User();

    OnFragmentInteractionListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_signup, container, false);
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
            this.loginActivity = (StartActivity) context;
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
            this.loginActivity = (StartActivity) activity;
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
        mTextViewAlreadyAccount = (TextView) view.findViewById(R.id.textview_already_account);
        mButtonSignup = (Button) view.findViewById(R.id.button_signup);
        mEditTextEmail = (EditText) view.findViewById(R.id.signup_form).findViewById(R.id.edittext_signup_email);
        mEditTextPassword = (EditText) view.findViewById(R.id.signup_form).findViewById(R.id.edittext_signup_password);
        mEditTextPhone = (EditText) view.findViewById(R.id.signup_form).findViewById(R.id.edittext_signup_phone);
        mEditTextFirstName = (EditText) view.findViewById(R.id.signup_form).findViewById(R.id.edittext_signup_first_name);
        mEditTextLastName = (EditText) view.findViewById(R.id.signup_form).findViewById(R.id.edittext_signup_last_name);
        mSpinnerGender = (Spinner) view.findViewById(R.id.signup_form).findViewById(R.id.spinner_signup_gender);
        mTextViewReport = (TextView) view.findViewById(R.id.textview_report_error_signup);
        mButtonFb = (Button) view.findViewById(R.id.button_facebook_signup);
        mButtonGoogle = (Button) view.findViewById(R.id.button_google_signup);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_frgment_signup);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);

        mTextViewReport.setOnClickListener(this);
        mButtonFb.setOnClickListener(this);
        mButtonGoogle.setOnClickListener(this);
        mTextViewAlreadyAccount.setOnClickListener(this);
        mButtonSignup.setOnClickListener(this);

        configEditTex(mEditTextEmail, linearLayout, "Email", R.drawable.icon_email);
        configEditTex(mEditTextPassword, linearLayout, "Password", R.drawable.icon_password);
        configEditTex(mEditTextPhone, linearLayout, "Phone", R.drawable.icon_email);
        configEditTex(mEditTextFirstName, linearLayout, "First Name", R.drawable.icon_email);
        configEditTex(mEditTextLastName, linearLayout, "Last Name", R.drawable.icon_email);

        String[] gender = getResources().getStringArray(R.array.gender);
        configSpinner(gender, mSpinnerGender);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview_already_account:
                listener.onViewClick(Constants.LOGIN_FRAGMENT);
                break;
            case R.id.button_signup:

                mUser.first_name = mEditTextFirstName.getText().toString();
                mUser.last_name = mEditTextLastName.getText().toString();
                mUser.email = mEditTextEmail.getText().toString();
                mUser.password_hash = mEditTextPassword.getText().toString();
                mUser.telephone = Integer.valueOf(mEditTextPhone.getText().toString());

                if(mSpinnerGender.getSelectedItem().toString().equals("Male")){
                    mUser.gender = 0;
                }
                else if(mSpinnerGender.getSelectedItem().toString().equals("Female")){
                    mUser.gender = 1;
                }
                else{
                    mUser.gender = 2;
                }

                logE(mUser.first_name +"||"+ mUser.last_name +"||"+
                        mUser.email +"||"+ mUser.password_hash +"||"+ mUser.telephone +"||"+ mUser.gender);

                listener.onViewClick(Constants.SIGNUP_BUTTON, mUser);
                break;
            case R.id.textview_report_error_signup:
                showShortToast(Constants.REPORT_ERROR);
                break;
            case R.id.button_facebook_signup:
                listener.onViewClick(Constants.LOGIN_FACEBOOK);
                break;
            case R.id.button_google_signup:
                listener.onViewClick(Constants.LOGIN_GOOGLE);
                break;
            default:
                break;
        }
        super.onClick(view);
    }
}

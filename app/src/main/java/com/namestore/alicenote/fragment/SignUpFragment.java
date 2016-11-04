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

public class SignUpFragment extends CoreFragment {
    EditText mEditTextEmail;
    EditText mEditTextPassword;
    EditText mEditTextReTypePassword;
    Button mButtonSignup;
    SwitchCompat switchCompatSignup;
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
        mEditTextEmail = (EditText) view.findViewById(R.id.editText_signup_email);
        mEditTextPassword = (EditText) view.findViewById(R.id.editText_signup_password);
        mEditTextReTypePassword = (EditText) view.findViewById(R.id.editText_signup_retype_password);
        switchCompatSignup = (SwitchCompat) view.findViewById(R.id.switch_compat_signup);
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
        configEditTex(mEditTextReTypePassword, linearLayout, "Re-type Password", R.drawable.icon_password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview_already_account:
                listener.onViewClick(Constants.LOGIN_FRAGMENT);
                break;
            case R.id.button_signup:

                mUser.email = mEditTextEmail.getText().toString();
                mUser.password_hash = mEditTextPassword.getText().toString();
                mUser.first_name = "";
                mUser.last_name = "";
                mUser.gender = 1;
                mUser.telephone = 1;

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

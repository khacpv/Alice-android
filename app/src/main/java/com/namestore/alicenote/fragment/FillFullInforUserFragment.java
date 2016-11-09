package com.namestore.alicenote.fragment;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.namestore.alicenote.R;
import com.namestore.alicenote.activity.LoginSignupActivity;

import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.dialog.DialogNotice;

import com.namestore.alicenote.models.User;
import com.namestore.alicenote.utils.AppUtils;
import com.namestore.alicenote.utils.ViewUtils;

import static com.namestore.alicenote.fragment.SignUpFragment.GENDER_FEMALE;
import static com.namestore.alicenote.fragment.SignUpFragment.GENDER_MALE;
import static com.namestore.alicenote.fragment.SignUpFragment.GENDER_OTHER;

/**
 * Created by kienht on 11/8/16.
 * Bắt user điền đầy đủ thông tin khi đăng nhập qua fb hoặc google plus
 */

public class FillFullInforUserFragment extends CoreFragment {

    EditText mEditTextEmail;
    EditText mEditTextPassword;
    EditText mEditTextPhone;
    EditText mEditTextFirstName;
    EditText mEditTextLastName;
    Spinner mSpinnerGender;
    Button mButtonOk;
    LinearLayout linearLayout;
    User mUser = new User();
    AppUtils appUtils;
    private LoginSignupActivity loginSignupActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_fillfull_inforuser, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModels();
        appUtils = new AppUtils(loginSignupActivity);
    }

    @Override
    protected void initViews(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_frgment_fill_infouser);
        mButtonOk = (Button) view.findViewById(R.id.button_ok);
        mEditTextEmail = (EditText) view.findViewById(R.id.fill_infouser_form).findViewById(R.id.edittext_signup_email);
        mEditTextPassword = (EditText) view.findViewById(R.id.fill_infouser_form).findViewById(R.id.edittext_signup_password);
        mEditTextPhone = (EditText) view.findViewById(R.id.fill_infouser_form).findViewById(R.id.edittext_signup_phone);
        mEditTextFirstName = (EditText) view.findViewById(R.id.fill_infouser_form).findViewById(R.id.edittext_signup_first_name);
        mEditTextLastName = (EditText) view.findViewById(R.id.fill_infouser_form).findViewById(R.id.edittext_signup_last_name);
        mSpinnerGender = (Spinner) view.findViewById(R.id.fill_infouser_form).findViewById(R.id.spinner_signup_gender);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);
        mButtonOk.setOnClickListener(this);
        mEditTextPassword.setVisibility(View.GONE);
        ViewUtils.configEditText(getActivity(), mEditTextEmail, linearLayout, "Email", R.drawable.icon_email, null);
        ViewUtils.configEditText(getActivity(), mEditTextPhone, linearLayout, "Phone", R.drawable.icon_email, null);
        ViewUtils.configEditText(getActivity(), mEditTextFirstName, linearLayout, "First Name", R.drawable.icon_email, null);
        ViewUtils.configEditText(getActivity(), mEditTextLastName, linearLayout, "Last Name", R.drawable.icon_email, null);

        String[] gender = getResources().getStringArray(R.array.gender);
        ViewUtils.configSpinner(getActivity(), gender, mSpinnerGender);

        mSpinnerGender.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager keyBoard =
                        (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                keyBoard.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);
                return false;
            }
        });

        fillUser();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof LoginSignupActivity) {
            this.loginSignupActivity = (LoginSignupActivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof LoginSignupActivity) {
            this.loginSignupActivity = (LoginSignupActivity) activity;
        }
    }

    public void fillUser() {
        if (loginSignupActivity != null) {
            mUser = loginSignupActivity.getUser();

            if (!TextUtils.isEmpty(mUser.firstName)) {
                fillEditText(mEditTextFirstName, mUser.firstName);
            }

            if (!TextUtils.isEmpty(mUser.lastName)) {
                fillEditText(mEditTextLastName, mUser.lastName);
            }
            if (!TextUtils.isEmpty(mUser.email)) {
                fillEditText(mEditTextEmail, mUser.email);
            }

            if (!TextUtils.isEmpty(mUser.telephone)) {
                fillEditText(mEditTextPhone, mUser.telephone);
            }
            if (mUser.gender != 0) {
                mSpinnerGender.setEnabled(false);
                switch (mUser.gender) {
                    case 1:
                        mSpinnerGender.setSelection(1);
                        break;
                    case 2:
                        mSpinnerGender.setSelection(2);
                        break;
                    case 3:
                        mSpinnerGender.setSelection(3);
                        break;
                }
            }
        }
    }

    private void fillEditText(EditText editText, String string) {
        editText.setText(string);
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
    }

    private void showDialog(String string) {
        DialogNotice dialogNotice = new DialogNotice();
        dialogNotice.showDialog(getActivity(), string);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_ok:

                mUser.firstName = mEditTextFirstName.getText().toString();
                mUser.lastName = mEditTextLastName.getText().toString();
                mUser.email = mEditTextEmail.getText().toString();
                mUser.telephone = mEditTextPhone.getText().toString();


                if (mSpinnerGender.getSelectedItem().toString().equals("Male")) {
                    mUser.gender = GENDER_MALE;
                } else if (mSpinnerGender.getSelectedItem().toString().equals("Female")) {
                    mUser.gender = GENDER_FEMALE;
                } else if (mSpinnerGender.getSelectedItem().toString().equals("Other")) {
                    mUser.gender = GENDER_OTHER;
                } else {
                    mUser.gender = 0;
                }

                if (TextUtils.isEmpty(mUser.firstName) || TextUtils.isEmpty(mUser.lastName)
                        || TextUtils.isEmpty(mUser.email) || TextUtils.isEmpty(mUser.telephone) || mUser.gender == 0) {
                    showDialog("Please filling in the blanks");
                } else {
                    if (appUtils.checkFirstLastName(mUser.firstName) || appUtils.checkFirstLastName(mUser.lastName)) {
                        if (appUtils.checkEmail(mUser.email)) {
                            if (mUser.telephone.length() < 10) {
                                showDialog("Phone phai lon hon 10 ky tu");
                            } else {
                                onFragmentInteractionListener.onViewClick(Constants.LOGIN_SOCIAL, mUser);
                            }
                        } else {
                            showDialog("Email sai dinh dang");
                        }
                    } else {
                        showDialog("Name sai dinh dang");
                    }
                }
                break;
        }

    }
}

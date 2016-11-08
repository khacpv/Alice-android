package com.namestore.alicenote.core;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.namestore.alicenote.data.Constants;

/**
 * Created by kienht on 10/25/16.
 */

public abstract class CoreActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void backPressed(Fragment fragment, int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(id, fragment)
                .commit();
    }

    public void logE(String mess) {
        Log.e(Constants.TAG, mess);
    }

    public void showShortToast(String msg) {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showKeyBoard(EditText mEditText) {
        InputMethodManager keyBoard =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyBoard.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideKeyBoard(RelativeLayout mRelativeLayout) {
        InputMethodManager keyBoard =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyBoard.hideSoftInputFromWindow(mRelativeLayout.getWindowToken(), 0);
    }

    public void configEditTex(final EditText editText, final RelativeLayout relativeLayout, final String hint, int icon) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText.setHint("");
                editText.requestFocusFromTouch();
                editText.setFocusableInTouchMode(true);
                showKeyBoard(editText);
                return false;
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editText.clearFocus();
                    editText.setHint(hint);
                    editText.setFocusableInTouchMode(false);
                }
            }
        });

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                relativeLayout.setFocusable(true);
                int childCount = relativeLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View view = relativeLayout.getChildAt(i);
                    view.clearFocus();
                }
                hideKeyBoard(relativeLayout);
                return false;
            }
        });
        editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
    }

}

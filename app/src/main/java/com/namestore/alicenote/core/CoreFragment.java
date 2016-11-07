package com.namestore.alicenote.core;

import android.app.Fragment;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.namestore.alicenote.data.Constants;

/**
 * Created by kienht on 10/24/16.
 */

public abstract class CoreFragment extends Fragment implements View.OnClickListener {

    public void logE(String mess) {
        Log.e(Constants.TAG, mess);
    }

    protected abstract void initViews(View view);

    protected abstract void initModels();


    @Override
    public void onClick(View view) {

    }

    public void showShortToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showKeyBoard(EditText mEditText) {
        InputMethodManager keyBoard =
                (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyBoard.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideKeyBoard(LinearLayout linearLayout) {
        InputMethodManager keyBoard =
                (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyBoard.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);
    }


    public void configEditTex(final EditText editText, final LinearLayout linearLayout, final String hint, int icon) {
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

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linearLayout.setFocusable(true);
                int childCount = linearLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View view = linearLayout.getChildAt(i);
                    view.clearFocus();
                }
                hideKeyBoard(linearLayout);
                return false;
            }
        });
        editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
    }

    public void configSpinner(String[] values, Spinner... spinners) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (Spinner spinner : spinners) {
            spinner.setAdapter(adapter);
            spinner.setSelection(0);
        }

    }

    public int getIntfromEdittex(EditText editText) {
        String string = editText.getText().toString();
        if (TextUtils.isEmpty(string)) {
            string = "0";
        }
        int integer = Integer.parseInt(string);
        return integer;
    }
}

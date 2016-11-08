package com.namestore.alicenote.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by kienht on 11/8/16.
 */

public class ViewUtils {


    public static void configEditText(final Activity activity, final EditText editText,
                                      final LinearLayout linearLayout, final String hint, int icon, final TextView textView) {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setHint("");
                editText.requestFocusFromTouch();
                editText.setFocusableInTouchMode(true);
                if (textView != null) {
                    textView.setVisibility(View.INVISIBLE);
                }

                InputMethodManager keyBoard =
                        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyBoard.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

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

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setFocusable(true);
                int childCount = linearLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View view = linearLayout.getChildAt(i);
                    view.clearFocus();
                }
                InputMethodManager keyBoard =
                        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyBoard.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);
            }
        });
        editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
    }


    public static void configSpinner(Activity activity, String[] values, Spinner... spinners) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (Spinner spinner : spinners) {
            spinner.setAdapter(adapter);
            spinner.setSelection(0);
        }

    }

}

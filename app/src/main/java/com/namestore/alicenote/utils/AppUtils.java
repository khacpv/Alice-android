package com.namestore.alicenote.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.namestore.alicenote.data.Constants;

/**
 * Created by kienht on 11/8/16.
 */

public class AppUtils{


    public static void logE(String mess) {
        Log.e(Constants.TAG, mess);
    }

    public static void showShortToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

}

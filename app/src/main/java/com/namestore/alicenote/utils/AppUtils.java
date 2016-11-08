package com.namestore.alicenote.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.namestore.alicenote.data.Constants;

import java.util.regex.Pattern;

/**
 * Created by kienht on 11/8/16.
 */

public class AppUtils {
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static final Pattern FISRT_LAST_NAME_PATTERN = Pattern.compile(
            "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]{3,10}+$"
    );

    public static void logE(String mess) {
        Log.e(Constants.TAG, mess);
    }

    public static void showShortToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static boolean checkFirstLastName(String name) {
        return FISRT_LAST_NAME_PATTERN.matcher(name).matches();
    }

}

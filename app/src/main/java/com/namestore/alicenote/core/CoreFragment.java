package com.namestore.alicenote.core;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.namestore.alicenote.data.Constants;

/**
 * Created by kienht on 10/24/16.
 */

public abstract class CoreFragment extends Fragment implements View.OnClickListener {

    protected CoreActivity mActivity;

    protected abstract void initViews(View view);

    protected abstract void initModels();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (CoreActivity) getActivity();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (CoreActivity) getActivity();
    }

    @Override
    public void onClick(View view) {

    }
}

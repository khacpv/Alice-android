package com.namestore.alicenote.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;


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

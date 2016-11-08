package com.namestore.alicenote.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;


/**
 * Created by kienht on 10/24/16.
 */

public abstract class CoreFragment extends Fragment implements View.OnClickListener {

    protected CoreActivity mActivity;
    public OnFragmentInteractionListener onFragmentInteractionListener;

    protected abstract void initViews(View view);

    protected abstract void initModels();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (CoreActivity) getActivity();

        try {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (CoreActivity) getActivity();
        try {
            onFragmentInteractionListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onClick(View view) {

    }
}

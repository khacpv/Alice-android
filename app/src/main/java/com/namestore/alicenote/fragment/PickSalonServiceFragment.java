package com.namestore.alicenote.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.activity.FirstSetupAcitivity;
import com.namestore.alicenote.adapter.MainServiceAdapter;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;
import com.namestore.alicenote.models.MainServices;

import java.util.ArrayList;

/**
 * Created by kienht on 10/26/16.
 */

public class PickSalonServiceFragment extends CoreFragment {

    Button mButtonBack;
    Button mButtonNext;
    TextView mTextViewTitle;
    ListView mListViewImageService;
    private FirstSetupAcitivity firstSetupAcitivity;
    OnFragmentInteractionListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_pick_salon_service, container, false);
        initViews(view);
        initModels();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) context;
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
        if (activity instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) activity;
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
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);
        mListViewImageService = (ListView) view.findViewById(R.id.listview_image_service);

        mTextViewTitle = (TextView) view.findViewById(R.id.title_pick_service).findViewById(R.id.title_first_setup);
        mTextViewTitle.setText("What services does\n\"Your Salon\" provide?");
    }

    @Override
    protected void initModels() {
        mButtonBack.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);

        int[] listImageViewService = {R.drawable.nail_service, R.drawable.hair_service,
                R.drawable.beauty_service, R.drawable.commingsoon};

        ArrayList<MainServices> listServices = new ArrayList<MainServices>();

        for (int i = 0; i < listImageViewService.length; i++) {
            MainServices mainServices = new MainServices();
            mainServices.setImgResId(listImageViewService[i]);
            listServices.add(mainServices);
        }
        mListViewImageService.setAdapter(new MainServiceAdapter(getActivity(), listServices));
        mListViewImageService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        listener.onViewClick(Constants.NAIL_SERVICE);
                        break;
                    case 1:
                        listener.onViewClick(Constants.HAIR_SERVICE);
                        break;
                    case 2:
                        showShortToast("BEAUTY SERVICE");
                        break;
                    case 3:
                        showShortToast("COMMING SOON SERVICE");
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                listener.onViewClick(Constants.TIME_OPEN_DOOR_FRAGMENT);
                break;
            case R.id.button_next:
                listener.onViewClick(Constants.CONFIG_SERVICE);
                break;

            default:
                break;
        }
        super.onClick(view);
    }

}

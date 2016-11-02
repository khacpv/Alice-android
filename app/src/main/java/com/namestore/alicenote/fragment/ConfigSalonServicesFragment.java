package com.namestore.alicenote.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.activity.FirstSetupAcitivity;
import com.namestore.alicenote.adapter.AllServicesAdapter;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;
import com.namestore.alicenote.models.SubServices;

import java.util.ArrayList;

/**
 * Created by kienht on 10/26/16.
 */

public class ConfigSalonServicesFragment extends CoreFragment {

    ArrayList<SubServices> servicesArrayList;
    private RecyclerView listNameServices;
    TextView mTextViewTitle;
    Button mButtonBack;
    Button mButtonNext;
    Button mButtonCancel;
    Button mButtonOk;
    Dialog configDialog;
    TextView mTextViewTitleDialog;
    private FirstSetupAcitivity firstSetupAcitivity;
    OnFragmentInteractionListener listener;

    public ConfigSalonServicesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_config_salon_services, container, false);
        initViews(view);
        initModels();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void dialogConfigService() {
        configDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        configDialog.setContentView(R.layout.layout_dialog_config_service);
        configDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        configDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        configDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        configDialog.setCancelable(false);
        mTextViewTitleDialog = (TextView) configDialog.findViewById(R.id.title_config_service_dialog);
        mButtonCancel = (Button) configDialog.findViewById(R.id.button_cancel);
        mButtonOk = (Button) configDialog.findViewById(R.id.button_ok);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configDialog.hide();
            }
        });

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configDialog.hide();
            }
        });
    }

    public void showDialog(String title) {
        if (configDialog != null) {
            mTextViewTitleDialog.setText(title);
            configDialog.show();
        }
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
        configDialog = new Dialog(firstSetupAcitivity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

        listNameServices = (RecyclerView) view.findViewById(R.id.list_services);
        listNameServices.setLayoutManager(new LinearLayoutManager(getActivity()));
        listNameServices.setHasFixedSize(true);

        mTextViewTitle = (TextView) view.findViewById(R.id.title_config_name_service).findViewById(R.id.title_first_setup);
        mTextViewTitle.setText("Prices and duarations\nof services at \"Your Salon\"");
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);
    }

    @Override
    protected void initModels() {

        mButtonBack.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        //dialogConfigService();

        servicesArrayList = new ArrayList<>();

        String listServices[] = getResources().getStringArray(R.array.list_service);
        String durationTimeService[] = getResources().getStringArray(R.array.duration_time_service);

        for (int i = 0; i < listServices.length; i++) {
            SubServices services = new SubServices();
            services.setNameSubServices(listServices[i]);
            this.servicesArrayList.add(services);
        }
        AllServicesAdapter adapter = new AllServicesAdapter(this.servicesArrayList, new AllServicesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SubServices item) {


            }
        }, durationTimeService, this.getActivity());

        listNameServices.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                listener.onViewClick(Constants.PICK_SERVICE);
                break;

            default:
                break;
        }
        super.onClick(view);
    }

}

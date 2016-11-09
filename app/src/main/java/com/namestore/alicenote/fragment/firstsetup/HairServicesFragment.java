package com.namestore.alicenote.fragment.firstsetup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.namestore.alicenote.utils.ViewUtils;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.activity.FirstSetupAcitivity;
import com.namestore.alicenote.adapter.SubServicesAdapter;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.models.SubServices;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kienht on 10/26/16.
 */

public class HairServicesFragment extends CoreFragment {

    ArrayList<SubServices> hairServicesArrayList;
    private RecyclerView recyclerViewNailService;
    TextView mTextViewTitle;
    EditText mEditTexAddHairService;
    Button mButtonBack;
    Button mButtonNext;
    Button mButtonAddService;
    LinearLayout linearLayout;
    private String newService;
    private ArrayList<String> arrayListHairService;
    SubServicesAdapter subServicesAdapter;

    private FirstSetupAcitivity firstSetupAcitivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_hair_service, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModels();
    }

    @Override
    protected void initViews(View view) {
        recyclerViewNailService = (RecyclerView) view.findViewById(R.id.list_hair_services);
        recyclerViewNailService.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewNailService.setHasFixedSize(true);

        mTextViewTitle = (TextView) view.findViewById(R.id.title_pick_hair_service).findViewById(R.id.title_first_setup);
        mTextViewTitle.setText("Hair Services");
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);
        mButtonAddService = (Button) view.findViewById(R.id.button_add_hair_service);
        mEditTexAddHairService = (EditText) view.findViewById(R.id.editTex_add_hair_service);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_frgment_hair_service);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);
        mButtonBack.setOnClickListener(this);
        mButtonNext.setVisibility(View.INVISIBLE);
        mButtonAddService.setOnClickListener(this);

        ViewUtils.configEditText(getActivity(), mEditTexAddHairService, linearLayout, "Add hair service", 0, null);

        hairServicesArrayList = new ArrayList<>();

        arrayListHairService = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.hair_list_services)));
        for (int i = 0; i < arrayListHairService.size(); i++) {
            SubServices subServices = new SubServices();
            subServices.setNameSubServices(arrayListHairService.get(i));
            this.hairServicesArrayList.add(subServices);
        }

        subServicesAdapter = new SubServicesAdapter(this.hairServicesArrayList);

        recyclerViewNailService.setAdapter(subServicesAdapter);
        recyclerViewNailService.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) context;
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) activity;
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).pickSalonService();
                }
                break;

            /**
             * Add hair Service
             * */
            case R.id.button_add_hair_service:
                newService = mEditTexAddHairService.getText().toString();
                SubServices temp = new SubServices();
                temp.setNameSubServices(newService);
                if (!TextUtils.isEmpty(newService)) {
                    mEditTexAddHairService.getText().clear();
                    subServicesAdapter.addItem(temp);
                    recyclerViewNailService.scrollToPosition(subServicesAdapter.getItemCount() - 1);
                }
                break;

            default:
                break;
        }
        super.onClick(view);
    }
}

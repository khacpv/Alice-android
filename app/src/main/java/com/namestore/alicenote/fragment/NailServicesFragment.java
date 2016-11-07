package com.namestore.alicenote.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.interfaces.OnFragmentInteractionListener;
import com.namestore.alicenote.models.SubServices;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kienht on 10/26/16.
 */
public class NailServicesFragment extends CoreFragment {

    ArrayList<SubServices> nailServicesArrayList;
    private RecyclerView recyclerViewNailService;
    TextView mTextViewTitle;
    EditText mEditTexAddNailService;
    Button mButtonBack;
    Button mButtonNext;
    Button mButtonAddService;
    LinearLayout linearLayout;
    private FirstSetupAcitivity firstSetupAcitivity;
    OnFragmentInteractionListener listener;
    private String newService;
    private ArrayList<String> arrayListNailService;
    SubServicesAdapter subServicesAdapter;

    public NailServicesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_config_nail_service, container, false);
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

        recyclerViewNailService = (RecyclerView) view.findViewById(R.id.list_nail_services);
        recyclerViewNailService.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewNailService.setHasFixedSize(true);

        mTextViewTitle = (TextView) view.findViewById(R.id.title_pick_nail_service).findViewById(R.id.title_first_setup);
        mTextViewTitle.setText("Nail Services");
        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);
        mButtonAddService = (Button) view.findViewById(R.id.button_add_nail_service);
        mEditTexAddNailService = (EditText) view.findViewById(R.id.editTex_add_nail_service);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_frgment_nail_service);
    }

    @Override
    protected void initModels() {
        linearLayout.setFocusable(true);
        mButtonBack.setOnClickListener(this);
        mButtonNext.setVisibility(View.INVISIBLE);
        mButtonAddService.setOnClickListener(this);
        configEditTex(mEditTexAddNailService, linearLayout, "Add nail service", 0, null);

        nailServicesArrayList = new ArrayList<>();

        arrayListNailService = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.nail_list_services)));
        for (int i = 0; i < arrayListNailService.size(); i++) {
            SubServices subServices = new SubServices();
            subServices.setNameSubServices(arrayListNailService.get(i));
            this.nailServicesArrayList.add(subServices);
        }

        subServicesAdapter = new SubServicesAdapter(this.nailServicesArrayList);

        recyclerViewNailService.setAdapter(subServicesAdapter);
        recyclerViewNailService.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                listener.onViewClick(Constants.PICK_SERVICE);
                break;

            /**
             * Add nail Service
             * */
            case R.id.button_add_nail_service:
                newService = mEditTexAddNailService.getText().toString();
                SubServices temp = new SubServices();
                temp.setNameSubServices(newService);
                if (!TextUtils.isEmpty(newService)) {
                    mEditTexAddNailService.getText().clear();
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

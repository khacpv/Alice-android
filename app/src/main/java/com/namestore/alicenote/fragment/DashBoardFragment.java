package com.namestore.alicenote.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.adapter.DashboardCustomRecyclerViewAdapter;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.models.DashboardObject;
import com.namestore.alicenote.models.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhocnhinho on 07/11/2016.
 */

public class DashBoardFragment extends CoreFragment {

    private TextView tvSaleIn;
    private List<DashboardObject> listViewContactUpComming = new ArrayList<>();
    private List<DashboardObject> listViewContactThisWeek = new ArrayList<>();
    private RecyclerView recyclerListViewUpComming, recyclerListViewThisWeek;
    private Button btnHideUpComming, btnHideThisWeek;
    private int checkHideUpComming, checkHideThisWeek;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_dashboard, container, false);
        initViews(view);
        initModels();
        return view;
    }
    @Override
    protected void initViews(View view) {

        tvSaleIn = (TextView) view.findViewById(R.id.tvSaleIn);
        recyclerListViewUpComming = (RecyclerView) view.findViewById(R.id.recyclerViewUpcommingAppointment);//listview cua upcoming
        btnHideUpComming = (Button) view.findViewById(R.id.btnHideUpComming);
        recyclerListViewThisWeek = (RecyclerView) view.findViewById(R.id.recyclerViewWeekAppointment);//listview cua thisweek
        btnHideThisWeek = (Button) view.findViewById(R.id.btnHideThisWeek);

        recyclerListViewUpComming.setLayoutManager(new LinearLayoutManager(getContext()));// de xuat hien dc recyclerview trong crollview
        recyclerListViewUpComming.setHasFixedSize(true);
        recyclerListViewThisWeek.setLayoutManager(new LinearLayoutManager(getContext()));// de xuat hien dc recyclerview trong crollview
        recyclerListViewThisWeek.setHasFixedSize(true);

    }





    @Override
    protected void initModels() {

        for (int i = 0; i < 6; i++) {
            DashboardObject apk = new DashboardObject(0, null, null, null, null, null);
            apk.setTvNameSevice("Classic Manicure");
            apk.setTvDate("");
            apk.setTvDuration("30 minutes");
            apk.setTvNameStaff("Demi Moore");
            apk.setTvTimeStart("10:30");

            listViewContactUpComming.add(apk);
        }


        DashboardCustomRecyclerViewAdapter adapter = new DashboardCustomRecyclerViewAdapter(getContext(), listViewContactUpComming);
        recyclerListViewUpComming.setAdapter(adapter);

        recyclerListViewUpComming.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                })
        );
        btnHideUpComming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkHideUpComming == 0) {
                    recyclerListViewUpComming.setVisibility(View.GONE);
                    btnHideUpComming.setText("SHOW");
                    checkHideUpComming = 1;
                } else {
                    recyclerListViewUpComming.setVisibility(View.VISIBLE);
                    btnHideUpComming.setText("HIDE");
                    checkHideUpComming = 0;
                }
            }
        });



        for (int i = 0; i < 6; i++) {
            DashboardObject apk = new DashboardObject(0, null, null, null, null, null);
            apk.setTvNameSevice("Classic Manicure");
            apk.setTvDate("Mon,Sep 29");
            apk.setTvDuration("31 minutes");
            apk.setTvNameStaff("Demi Moore");
            apk.setTvTimeStart("10:30");

            listViewContactThisWeek.add(apk);
        }


        DashboardCustomRecyclerViewAdapter adapterThisWeek = new DashboardCustomRecyclerViewAdapter(getContext(), listViewContactThisWeek);
        recyclerListViewThisWeek.setAdapter(adapterThisWeek);
        recyclerListViewThisWeek.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                })
        );
        btnHideThisWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkHideThisWeek == 0) {
                    recyclerListViewThisWeek.setVisibility(View.GONE);
                    btnHideThisWeek.setText("SHOW");
                    checkHideThisWeek = 1;
                } else {
                    recyclerListViewThisWeek.setVisibility(View.VISIBLE);
                    btnHideThisWeek.setText("HIDE");
                    checkHideThisWeek = 0;
                }
            }
        });
    }
}

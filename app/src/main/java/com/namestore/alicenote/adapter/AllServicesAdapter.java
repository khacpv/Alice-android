package com.namestore.alicenote.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.models.SubServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kienht on 10/26/16.
 */

public class AllServicesAdapter extends RecyclerView.Adapter<AllServicesAdapter.ViewHolder> {

    private ArrayList<SubServices> servicesArrayList;
    private OnItemClickListener listener;
    private static String[] durationTimeService;
    private static Activity activity;


    public AllServicesAdapter(List<SubServices> services, OnItemClickListener listener, String durationTimeService[], Activity activity) {
        this.servicesArrayList = new ArrayList<>(services);
        this.listener = listener;
        this.durationTimeService = durationTimeService;
        this.activity = activity;
    }

    public interface OnItemClickListener {
        void onItemClick(SubServices item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_salon_service_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(servicesArrayList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return servicesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private LinearLayout linearLayout;
        private Button mButtonDown;
        private Spinner mSpinner;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.name_service_name);
            mButtonDown = (Button) itemView.findViewById(R.id.button_down);
            mSpinner = (Spinner) itemView.findViewById(R.id.spinner_duation_time);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_config_services);
            linearLayout.setVisibility(View.GONE);
        }

        public void bindData(final SubServices services, final OnItemClickListener listener) {
            mTextView.setText(services.getNameSubServices());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(services);

                    if (linearLayout.getVisibility() != View.VISIBLE) {
                        linearLayout.setVisibility(View.VISIBLE);
                        mButtonDown.setBackgroundResource(R.drawable.icon_up);
                    } else {
                        linearLayout.setVisibility(View.GONE);
                        mButtonDown.setBackgroundResource(R.drawable.icon_down);
                    }
                }
            });
            mButtonDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.performClick();
                }
            });

            settingSpinner();

        }

        public void settingSpinner() {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, durationTimeService);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpinner.setAdapter(adapter);
            mSpinner.setSelection(0);

        }
    }

}

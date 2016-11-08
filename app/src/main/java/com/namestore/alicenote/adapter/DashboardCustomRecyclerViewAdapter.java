package com.namestore.alicenote.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.models.DashboardObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nhocnhinho on 11/18/2015.
 */
public class DashboardCustomRecyclerViewAdapter extends
        RecyclerView.Adapter<DashboardCustomRecyclerViewAdapter.RecyclerViewHolder> {
    Context context;
    private List<DashboardObject> Dashboard_Object = new ArrayList<DashboardObject>();

    public DashboardCustomRecyclerViewAdapter(Context context, List<DashboardObject> listData) {
        this.Dashboard_Object = listData;
        this.context =context;
    }

    public void updateList(List<DashboardObject> data) {
        Dashboard_Object = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return Dashboard_Object.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                 int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout_dashboard_item, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, final int position) {
        viewHolder.tvNameSevice.setText(Dashboard_Object.get(position).getTvNameSevice());
        viewHolder.tvDate.setText(Dashboard_Object.get(position).getTvDate());
        viewHolder.tvTimeStart.setText(Dashboard_Object.get(position).getTvTimeStart());
        viewHolder.tvNameStaff.setText(Dashboard_Object.get(position).getTvNameStaff());
        viewHolder.tvDuration.setText(Dashboard_Object.get(position).getTvDuration());

    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            OnClickListener {

        private TextView tvNameSevice;
        private TextView tvDate;
        private TextView tvTimeStart;
        private TextView tvNameStaff;
        private TextView tvDuration;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvNameSevice = (TextView) itemView.findViewById(R.id.tvNameSevice);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTimeStart = (TextView) itemView.findViewById(R.id.tvTimeStart);
            tvNameStaff = (TextView) itemView.findViewById(R.id.tvNameStaff);
            tvDuration = (TextView) itemView.findViewById(R.id.tvDuration);




        }

        // remove main_layout_recyclerview_item when click button delete
        @Override
        public void onClick(View v) {

        }

    }

}

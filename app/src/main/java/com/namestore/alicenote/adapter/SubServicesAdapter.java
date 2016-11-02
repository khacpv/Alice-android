package com.namestore.alicenote.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.models.SubServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kienht on 11/2/16.
 */

public class SubServicesAdapter extends RecyclerView.Adapter<SubServicesAdapter.ViewHolder> {

    ArrayList<SubServices> subServices;

    public SubServicesAdapter(List<SubServices> subServices){
        this.subServices = new ArrayList<>(subServices);
    }

    @Override
    public SubServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subservices_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SubServicesAdapter.ViewHolder holder, int position) {
        holder.bindData(subServices.get(position));

        //in some cases, it will prevent unwanted situations
        holder.checkboxService.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        holder.checkboxService.setChecked(subServices.get(position).isSelected());

        holder.checkboxService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subServices.get(holder.getAdapterPosition()).setSelected(isChecked);
            }
        });
    }

    public void addItem(SubServices item) {
        subServices.add(item);
        notifyItemInserted(subServices.size() - 1);
    }

    public void addItem(int position, SubServices item) {
        subServices.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        subServices.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(SubServices item) {
        int index = subServices.indexOf(item);
        if (index < 0)
            return;
        subServices.remove(index);
        notifyItemRemoved(index);
    }

    public void replaceItem(int postion, SubServices item) {
        subServices.remove(postion);
        subServices.add(postion, item);
        notifyItemChanged(postion);
    }

    @Override
    public int getItemCount() {
        return subServices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView textNameServices;
        private CheckBox checkboxService;
        private Button buttonDelete;

        public ViewHolder(final View v) {
            super(v);
            textNameServices = (TextView) v.findViewById(R.id.sub_service_name);
            checkboxService = (CheckBox) v.findViewById(R.id.sub_service_checkbox);
            buttonDelete = (Button) v.findViewById(R.id.sub_service_delete);
        }

        public void bindData(SubServices subServices) {
            textNameServices.setText(subServices.getNameSubServices());
            textNameServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkboxService.isChecked()) {
                        checkboxService.setChecked(false);
                    } else {
                        checkboxService.setChecked(true);
                    }
                }
            });
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(getAdapterPosition());
                    buttonDelete.setClickable(false);
                }
            });
        }
    }

}

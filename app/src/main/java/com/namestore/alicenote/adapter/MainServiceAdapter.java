package com.namestore.alicenote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.models.MainServices;

import java.util.ArrayList;

/**
 * Created by kienht on 10/31/16.
 */

public class MainServiceAdapter extends BaseAdapter {

    ArrayList<MainServices> mainServices = new ArrayList<MainServices>();
    LayoutInflater inflater;
    Context context;

    public MainServiceAdapter(Context context, ArrayList<MainServices> myList) {
        this.mainServices = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return mainServices.size();
    }

    @Override
    public MainServices getItem(int position) {
        return mainServices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview_service, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        MainServices currentListData = getItem(position);

        mViewHolder.imageView.setImageResource(currentListData.getImgResId());

        return convertView;
    }

    private class MyViewHolder {
        ImageView imageView;

        public MyViewHolder(View item) {
            imageView = (ImageView) item.findViewById(R.id.imageview_item_service);

        }

    }


}

package com.hongri.lottie.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hongri.lottie.R;
import com.hongri.lottie.holder.NormalHolder;

/**
 * @author zhongyao
 * @date 2018/11/21
 */

public class NormalAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<String> mData;
    private LayoutInflater mInflater;

    public NormalAdapter(Context context, ArrayList<String> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_refresh_item, parent, false);
        return new NormalHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((NormalHolder)holder).tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

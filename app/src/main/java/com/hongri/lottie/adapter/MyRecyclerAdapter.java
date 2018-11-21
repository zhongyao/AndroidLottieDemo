package com.hongri.lottie.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hongri.lottie.R;
import com.hongri.lottie.holder.FooterHolder;
import com.hongri.lottie.holder.HeaderHolder;
import com.hongri.lottie.holder.NormalHolder;

/**
 * @author zhongyao
 * @date 2018/11/16
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<String> mData;
    private LayoutInflater mInflater;
    private final int mHeaderSize = 1;
    private final int mFooterSize = 1;

    private final int HEADER_TYPE = 1;
    private final int FOOTER_TYPE = 2;
    private final int NORMAL_TYPE = 3;

    private View mHeaderView;
    private View mFooterView;

    public MyRecyclerAdapter(Context context, ArrayList<String> data, View headerView, View footerView) {
        mContext = context;
        mData = data;
        mHeaderView = headerView;
        mFooterView = footerView;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder mHolder = null;
        View view;
        if (viewType == HEADER_TYPE) {
            mHolder = new HeaderHolder(mHeaderView);
        } else if (viewType == FOOTER_TYPE) {
            mHolder = new FooterHolder(mFooterView);
        } else {
            view = mInflater.inflate(R.layout.activity_refresh_item, parent, false);
            mHolder = new NormalHolder(view);
        }
        return mHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((HeaderHolder)holder).tvRefresh.setText(R.string.pulldown_refresh);
        } else if (position == getItemCount() - 1) {
            ((FooterHolder)holder).tvFooter.setText(R.string.load_more);
        } else {
            ((NormalHolder)holder).tv.setText(mData.get(position - 1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            //Header
            return HEADER_TYPE;
        } else if (position == getItemCount() - 1) {
            //Footer
            return FOOTER_TYPE;
        } else {
            //Normal
            return NORMAL_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + mHeaderSize + mFooterSize;
    }
}

package com.hongri.lottie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hongri.lottie.R;
import com.hongri.lottie.holder.FooterHolder;
import com.hongri.lottie.holder.HeaderHolder;

/**
 * @author zhongyao
 * @date 2018/11/16
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private final int mHeaderSize = 1;
    private final int mFooterSize = 1;

    private final int HEADER_TYPE = 1;
    private final int FOOTER_TYPE = 2;
    private final int NORMAL_TYPE = 3;

    private NormalAdapter mNormalAdapter;
    private View mHeaderView;
    private View mFooterView;
    private TextView mRefreshTitle;
    private ImageView mRefreshIcon;

    public MyRecyclerAdapter(Context context, NormalAdapter normalAdapter, View headerView,
                             View footerView) {
        mContext = context;
        mNormalAdapter = normalAdapter;
        mHeaderView = headerView;
        mFooterView = footerView;
    }

    //public void setHeaderData(HeaderItem headerItem){
    //    mRefreshTitle = headerItem.getRefreshTitle();
    //    mRefreshIcon = headerItem.getRefreshIcon()
    //}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder mHolder;
        if (viewType == HEADER_TYPE) {
            mHolder = new HeaderHolder(mHeaderView);
        } else if (viewType == FOOTER_TYPE) {
            mHolder = new FooterHolder(mFooterView);
        } else {
            mHolder = mNormalAdapter.onCreateViewHolder(parent, viewType);

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
            mNormalAdapter.onBindViewHolder(holder, position - 1);
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
        return mNormalAdapter.getItemCount() + mHeaderSize + mFooterSize;
    }
}

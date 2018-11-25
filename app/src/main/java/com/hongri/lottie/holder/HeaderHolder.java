package com.hongri.lottie.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.hongri.lottie.R;

/**
 * @author zhongyao
 * @date 2018/11/20
 */

public class HeaderHolder extends RecyclerView.ViewHolder {

    public TextView tvRefresh;

    public HeaderHolder(View itemView) {
        super(itemView);
        tvRefresh = (TextView)itemView.findViewById(R.id.tv_pulldownrefresh);
    }
}

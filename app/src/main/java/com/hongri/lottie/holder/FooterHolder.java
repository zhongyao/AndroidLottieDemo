package com.hongri.lottie.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.hongri.lottie.R;

/**
 * @author zhongyao
 * @date 2018/11/20
 */

public class FooterHolder extends RecyclerView.ViewHolder {

    public TextView tvFooter;

    public FooterHolder(View itemView) {
        super(itemView);
        tvFooter = (TextView)itemView.findViewById(R.id.tv_footer);
    }
}

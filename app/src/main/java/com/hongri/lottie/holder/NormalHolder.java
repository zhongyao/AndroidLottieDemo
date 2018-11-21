package com.hongri.lottie.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.hongri.lottie.R;

/**
 * @author zhongyao
 * @date 2018/11/20
 */

public class NormalHolder extends RecyclerView.ViewHolder {
    public TextView tv;

    public NormalHolder(View itemView) {
        super(itemView);

        tv = (TextView)itemView.findViewById(R.id.tv);
    }
}

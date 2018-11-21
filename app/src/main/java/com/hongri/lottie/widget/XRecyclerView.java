package com.hongri.lottie.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.hongri.lottie.util.Logger;

/**
 * @author zhongyao
 * @date 2018/11/16
 */

public class XRecyclerView extends RecyclerView {
    private View mHeaderView;
    private View mFooterView;
    private boolean isFirstComeIn = true;
    private int mHeaderMeasureHeight;

    public XRecyclerView(Context context,
                         @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        Logger.d("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Logger.d("onLayout");
        //if (isFirstComeIn) {
        //    mHeaderMeasureHeight = mHeaderView.getMeasuredHeight();
        //    mHeaderView.setTop(-mHeaderMeasureHeight);
        //    LayoutParams params = (LayoutParams)mHeaderView.getLayoutParams();
        //    params.setMargins(params.leftMargin, -mHeaderMeasureHeight, params.rightMargin,
        //        params.bottomMargin);
        //    mHeaderView.setLayoutParams(params);
        //    isFirstComeIn = false;
        //}
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }
}

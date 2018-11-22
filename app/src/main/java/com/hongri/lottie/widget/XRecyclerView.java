package com.hongri.lottie.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
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
    private boolean isBeingDraged = false;
    private float mLastMotionX, mLastMotionY;
    private int mTouchSlop;
    private boolean mIsBeingDragged = false;

    public XRecyclerView(Context context,
                         @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
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
        if (isFirstComeIn) {
            if (mHeaderMeasureHeight == 0) {
                mHeaderMeasureHeight = mHeaderView.getMeasuredHeight();
            }
            updateHeaderMargin(-mHeaderMeasureHeight);
            isFirstComeIn = false;
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        Logger.d("state:" + state + "getChildAt(0):" + getChildAt(0));
        if (state == RecyclerView.SCROLL_STATE_SETTLING && mHeaderView == getChildAt(0)) {
            updateHeaderMargin(-mHeaderMeasureHeight);
        }
    }

    private void updateHeaderMargin(int marginTop) {
        //mHeaderView.setTop(-mHeaderMeasureHeight/2);
        LayoutParams params = (LayoutParams)mHeaderView.getLayoutParams();
        params.setMargins(params.leftMargin, marginTop, params.rightMargin,
            params.bottomMargin);
        mHeaderView.setLayoutParams(params);
    }

    /**
     * 1、首先判断列表是否滑动到了顶部
     * 2、当列表在顶部时向下拉动，则触发Touch事件
     *
     * @param e
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logger.d("onTouchEvent---ACTION_DOWN");
                mLastMotionX = e.getX();
                mLastMotionY = e.getY();

                mIsBeingDragged = false;

                break;
            case MotionEvent.ACTION_MOVE:
                Logger.d("onTouchEvent---ACTION_MOVE");
                float offsetX = Math.abs(e.getX() - mLastMotionX);
                float offsetY = Math.abs(e.getY() - mLastMotionY);
                if (offsetY > offsetX && offsetY >= mTouchSlop) {
                    //表示是上下滑动,拦截事件交给OnTouchEvent事件处理
                    mIsBeingDragged = true;
                    Logger.d("上下滑动:");
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                Logger.d("onTouchEvent---ACTION_UP");

                break;
            default:
                break;
        }
        return mIsBeingDragged;
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

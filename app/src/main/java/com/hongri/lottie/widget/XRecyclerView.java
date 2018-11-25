package com.hongri.lottie.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hongri.lottie.R;
import com.hongri.lottie.bean.RefreshState;
import com.hongri.lottie.holder.HeaderHolder;
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
    private float mMaxVisibleHeight = 350;

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
        if (state == RecyclerView.SCROLL_STATE_SETTLING && mHeaderView == getChildAt(0)) {
            Logger.d("state:" + state + "getChildAt(0):" + getChildAt(0));
            //updateHeaderMargin(-mHeaderMeasureHeight);
        }
    }

    private void updateHeaderMargin(int marginTop) {
        mHeaderView.setTop(marginTop);
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
        if (mHeaderView == getChildAt(0)) {
            //Logger.d("getChildAt(0):is mHeaderView" + true);
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //Logger.d("onTouchEvent---ACTION_DOWN");
                    mLastMotionX = e.getX();
                    mLastMotionY = e.getY();

                    //mIsBeingDragged = false;

                    break;
                case MotionEvent.ACTION_MOVE:
                    //Logger.d("onTouchEvent---ACTION_MOVE");
                    float offsetX = e.getX() - mLastMotionX;
                    float offsetY = e.getY() - mLastMotionY;
                    if (Math.abs(offsetY) > Math.abs(offsetX) && offsetY >= mTouchSlop) {
                        //表示是上下滑动,拦截事件交给OnTouchEvent事件处理
                        mIsBeingDragged = true;
                        if (offsetY <= mMaxVisibleHeight) {
                            //如果下拉的高度小于最大的下拉高度
                            if (offsetY < mHeaderMeasureHeight) {
                                setRefreshState(RefreshState.PULL_TO_REFRESH);
                            } else {
                                setRefreshState(RefreshState.RELEASE_TO_REFRESH);
                            }
                            updateHeaderMargin(-mHeaderMeasureHeight + (int)offsetY);
                        }
                        return true;
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    updateHeaderMargin(0);
                    setRefreshState(RefreshState.REFRESHING);
                    Logger.d("onTouchEvent---ACTION_UP");

                    break;
                default:
                    break;
            }
        } else {
            //Logger.d("getChildAt(0):is mHeaderView" + false);
        }
        return super.onTouchEvent(e);
    }

    private void setRefreshState(int state) {

        View view = getChildAt(0);
        TextView mPullDownRefreshTv = null;
        LinearLayout mRefreshing = null;
        if (getChildViewHolder(view) instanceof HeaderHolder) {
            mPullDownRefreshTv = (TextView)view.findViewById(R.id.tv_pulldownrefresh);
            mRefreshing = (LinearLayout)view.findViewById(R.id.ll_refreshing);
        }

        switch (state) {
            case RefreshState.PULL_TO_REFRESH:
                mRefreshing.setVisibility(INVISIBLE);
                mPullDownRefreshTv.setVisibility(VISIBLE);
                mPullDownRefreshTv.setText("下拉刷新");

                Logger.d("PULL_TO_REFRESH");
                break;
            case RefreshState.RELEASE_TO_REFRESH:
                mRefreshing.setVisibility(INVISIBLE);
                mPullDownRefreshTv.setVisibility(VISIBLE);
                mPullDownRefreshTv.setText("松开刷新");

                Logger.d("RELEASE_TO_REFRESH");
                break;
            case RefreshState.REFRESHING:
                mRefreshing.setVisibility(VISIBLE);
                mPullDownRefreshTv.setVisibility(INVISIBLE);

                requestData();
                Logger.d("REFRESHING");
                break;
            case RefreshState.RESET:
                mRefreshing.setVisibility(INVISIBLE);
                mPullDownRefreshTv.setVisibility(INVISIBLE);

                updateHeaderMargin(-mHeaderMeasureHeight);
                Logger.d("RESET");
                break;
            default:
                break;
        }
    }

    /**
     * 请求网络数据(模拟)
     */
    private void requestData() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefreshState(RefreshState.RESET);
            }
        }, 3000);
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

package com.hongri.lottie.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.hongri.lottie.util.DataUtil;

/**
 * @author zhongyao
 * @date 2018/11/21
 */

public class BottomLinearLayout extends LinearLayout {

    private final int BOTTOM_LAYOUT_HEIGHT = 116;
    private Context mContext;

    public BottomLinearLayout(Context context) {
        super(context);
        mContext = context;
    }

    public void addSubView() {

        setBackgroundColor(Color.RED);

        FrameLayout.LayoutParams dividerLayoutParams = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(mContext, BOTTOM_LAYOUT_HEIGHT),
            Gravity.TOP);
        dividerLayoutParams.leftMargin = dividerLayoutParams.rightMargin = 40;
        setLayoutParams(dividerLayoutParams);

        setOrientation(VERTICAL);
        View viewLine1 = new View(mContext);
        View viewLine2 = new View(mContext);

        LinearLayout.LayoutParams subLineParams1 = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(mContext, BOTTOM_LAYOUT_HEIGHT) / 2);
        subLineParams1.leftMargin = subLineParams1.rightMargin = 40;
        ;
        subLineParams1.gravity = Gravity.CENTER_HORIZONTAL;

        viewLine1.setLayoutParams(subLineParams1);
        viewLine1.setBackgroundColor(Color.GREEN);

        LinearLayout.LayoutParams subLineParams2 = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(mContext, BOTTOM_LAYOUT_HEIGHT) / 2);
        subLineParams2.leftMargin = subLineParams2.rightMargin = 40;
        ;
        subLineParams2.gravity = Gravity.CENTER_HORIZONTAL;

        viewLine2.setLayoutParams(subLineParams2);
        viewLine2.setBackgroundColor(Color.LTGRAY);

        //removeView(this);
        addView(viewLine1);

        addView(viewLine2);

    }
}

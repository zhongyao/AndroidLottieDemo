package com.hongri.lottie.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.hongri.lottie.R;
import com.hongri.lottie.util.DataUtil;

/**
 *
 * @author zhongyao
 * @date 2018/11/21
 */

public class BottomRelativeLayout extends RelativeLayout {
    private final int BOTTOM_LAYOUT_HEIGHT = 116;
    private Context mContext;

    public BottomRelativeLayout(Context context) {
        super(context);
        mContext = context;
    }

    @SuppressLint("ResourceType")
    public void addRelativeSubLayout(){

        FrameLayout.LayoutParams dividerLayoutParams = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(mContext, BOTTOM_LAYOUT_HEIGHT),
            Gravity.TOP);
        dividerLayoutParams.leftMargin = dividerLayoutParams.rightMargin = 40;
        setLayoutParams(dividerLayoutParams);
        //setId(1111);

        View viewLine1 = new View(mContext);
        View viewLine2 = new View(mContext);

        RelativeLayout.LayoutParams subLineParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(mContext, BOTTOM_LAYOUT_HEIGHT) / 2);
        subLineParams1.leftMargin = subLineParams1.rightMargin = 40;

        viewLine1.setId(R.id.general_card_divider_line_id);
        viewLine1.setLayoutParams(subLineParams1);
        viewLine1.setBackgroundColor(Color.GREEN);

        RelativeLayout.LayoutParams subLineParams2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(mContext, BOTTOM_LAYOUT_HEIGHT) / 2);
        subLineParams2.leftMargin = subLineParams2.rightMargin = 40;
        subLineParams2.addRule(RelativeLayout.BELOW,viewLine1.getId());

        viewLine2.setLayoutParams(subLineParams2);
        viewLine2.setBackgroundColor(Color.LTGRAY);

        //removeView(this);
        addView(viewLine1);

        addView(viewLine2);

    }
}

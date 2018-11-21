package com.hongri.lottie;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhongyao on 2018/11/15.
 */

public class CustomImageView extends AppCompatImageView {
    private TextView text;
    private Context mContext;
    public CustomImageView(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Toast.makeText(mContext,"onDraw",Toast.LENGTH_SHORT).show();

        postDelayed(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(mContext,"哈哈哈",Toast.LENGTH_SHORT).show();
                //text.setText("哈哈");
            }
        },2000);
    }

    public void setText(TextView text) {
        invalidate();
        this.text = text;
    }
}

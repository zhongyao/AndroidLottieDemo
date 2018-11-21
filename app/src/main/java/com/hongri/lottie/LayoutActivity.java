package com.hongri.lottie;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hongri.lottie.util.DataUtil;

/**
 * @author hongri
 */
public class LayoutActivity extends AppCompatActivity {
    private final int TOTAL_HEIGHT = 116;
    private final int IMAGE_SIZE = 24;
    private final int IMAGE_BOTTOM_MARGIN = 10;
    private final int TEXT_LAYOUT_BOTTOM_MARGIN = 5;
    private final int TEXT_BOTTOM_MARGIN = 5;

    private final int TEXT_LAYOUT_HEIGHT = 116;
    private final int TEXT_HEIGHT = 24;

    @RequiresApi(api = VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_layout);

        /**
         * 根布局
         */
        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(this, TOTAL_HEIGHT));
        frameLayoutParams.gravity = Gravity.BOTTOM;
        frameLayout.setLayoutParams(frameLayoutParams);
        frameLayout.setBackgroundColor(Color.YELLOW);

        /**
         * 图片
         */
        final CustomImageView imageView = new CustomImageView(this);
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(this, IMAGE_SIZE));
        imageParams.gravity = Gravity.BOTTOM;
        imageParams.bottomMargin = DataUtil.getDimen(this, IMAGE_BOTTOM_MARGIN);
        imageView.setLayoutParams(imageParams);
        imageView.setImageResource(R.drawable.ic_launcher_background);

        /**
         * 文本根布局
         */
        LinearLayout textLaout = new LinearLayout(this);
        FrameLayout.LayoutParams textLayoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT);
        textLayoutParams.gravity = Gravity.BOTTOM;
        textLayoutParams.bottomMargin = DataUtil.getDimen(this, TEXT_LAYOUT_BOTTOM_MARGIN);

        textLaout.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        textLaout.setLayoutParams(textLayoutParams);

        textLaout.setBackgroundColor(Color.GRAY);

        /**
         * 文本布局
         */
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
            DataUtil.getDimen(this, TEXT_HEIGHT));
        textParams.gravity = Gravity.BOTTOM;
        textParams.bottomMargin = DataUtil.getDimen(this, TEXT_BOTTOM_MARGIN);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(50);
        drawable.setColor(Color.GREEN);

        textView.setBackground(drawable);
        textView.setText("恭喜您刷新成功");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(textParams);

        textLaout.addView(textView);

        frameLayout.addView(imageView);
        frameLayout.addView(textLaout);

        //final CustomImageView customImageView = new CustomImageView(this);
        //imageView.setText(textView);

        //textLaout.setOnClickListener(new OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        imageView.requestLayout();
        //        imageView.invalidate();
        //    }
        //});
        setContentView(frameLayout);
    }

}

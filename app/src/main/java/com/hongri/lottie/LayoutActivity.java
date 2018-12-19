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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hongri.lottie.util.DataUtil;
import com.hongri.lottie.widget.BottomRelativeLayout;

/**
 * @author hongri
 */
public class LayoutActivity extends AppCompatActivity {
    private final int TOTAL_HEIGHT = 116 * 4;
    private final int IMAGE_SIZE = 24;
    private final int IMAGE_BOTTOM_MARGIN = 10;
    private final int TEXT_LAYOUT_BOTTOM_MARGIN = 5;
    private final int TEXT_BOTTOM_MARGIN = 5;
    private final int BOTTOM_LAYOUT_HEIGHT = 116;

    private final int TEXT_LAYOUT_HEIGHT = 116;
    private final int TEXT_HEIGHT = 24;
    private ImageView iv;

    @RequiresApi(api = VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
        //setContentView(R.layout.activity_layout);

        /**
         * 原来icon是个黑色订阅图片，color设置为red
         * 加上不同Mode的ColorFilter之后的效果如下：
         *
         * PorterDuff模式	都是透明的	都是不透明的	只在红色的圆形图片中是透明的	只在蓝色的方形图片中是透明的
         CLEAR	            0	0	0	0
         SRC	            0	B	B	0
         DST	            0	R	0	R
         SRC_OVER	        0	B	B	R
         DST_OVER	        0	R	B	R
         SRC_IN         	0	B	0	0
         DST_IN	            0	R	0	0
         SRC_OUT	        0	0	B	0
         DST_OUT	        0	0	0	R
         SRC_ATOP	        0	B	0	R
         DST_ATOP	        0	R	B	0
         XOR	            0	0	B	R

         链接：https://www.jianshu.com/p/9cae2250d0ed
         */
        //iv = (ImageView)findViewById(R.id.iv);
        //1、CLEAR 透明
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.CLEAR);
        //2、SRC 全红
        //iv.setColorFilter(ContextCompat.getColor(this, R.color.color_red), Mode.SRC);
        //3、DST 正常显示原图
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red),Mode.DST);
        //4、SRC_OVER 红色全覆盖
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.SRC_OVER);
        //5、DST_OVER 黑色icon在上，红色背景在下
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.DST_OVER);
        //6、SRC_IN 黑色icon替换成红色icon
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.SRC_IN);
        //7、DST_IN 正常显示
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.DST_IN);
        //8、SRC_OUT icon区域透明，背景是红色的区域
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.SRC_OUT);
        //9、DST_OUT 透明
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.DST_OUT);
        //10、SRC_ATOP
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.SRC_ATOP);
        //11、DST_ATOP
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.DST_ATOP);
        //12、XOR
        //iv.setColorFilter(ContextCompat.getColor(this,R.color.color_red), Mode.XOR);
        //iv.setImageResource(R.drawable.add_item);

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
         * 自定义LinearLayout布局
         */
        //BottomLinearLayout bottomLayout = new BottomLinearLayout(this);
        //FrameLayout.LayoutParams bottomLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams
        // .MATCH_PARENT,
        //    DataUtil.getDimen(this, DataUtil.getDimen(this,BOTTOM_LAYOUT_HEIGHT)));
        //bottomLayoutParams.gravity = Gravity.TOP;
        //bottomLayout.setLayoutParams(bottomLayoutParams);
        ////bottomLayout.setBackgroundColor(Color.BLUE);
        //
        //bottomLayout.addSubView();

        /**
         * 自定义RelativeLayout布局
         */
        BottomRelativeLayout bottomRelativeLayout = new BottomRelativeLayout(this);
        FrameLayout.LayoutParams bottomRelativeParams = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            DataUtil.getDimen(this, DataUtil.getDimen(this, BOTTOM_LAYOUT_HEIGHT)));
        bottomRelativeParams.gravity = Gravity.TOP;
        bottomRelativeLayout.setLayoutParams(bottomRelativeParams);
        bottomRelativeLayout.setBackgroundColor(Color.BLUE);

        bottomRelativeLayout.addRelativeSubLayout();

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
            DataUtil.getDimen(this, TOTAL_HEIGHT / 2));
        textLayoutParams.gravity = Gravity.BOTTOM;
        textLayoutParams.bottomMargin = DataUtil.getDimen(this, TEXT_LAYOUT_BOTTOM_MARGIN);

        textLaout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
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

        frameLayout.addView(bottomRelativeLayout);
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
        //setContentView(frameLayout);
    }

}

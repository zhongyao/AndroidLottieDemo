package com.hongri.lottie;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.airbnb.lottie.Cancellable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.hongri.lottie.util.DataUtil;
import com.hongri.lottie.util.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 该项目主要是介绍Lottie动画的。其中以下拉刷新的实例来进行Lottie动画实例的演示。
 *
 * @author hongri
 *         参考：
 *         https://juejin.im/entry/58a324d12f301e00695da316
 *         Lottie动画的实现原理：
 *         json--->Component--->Drawable--->View
 *         核心类如下：
 *         LottieComposition
 *         LottieDrawable
 *         LottieAnimationView
 *         1、解析json文件
 *         2、建立数据到对象的映射
 *         3、根据数据对象创建合适的Drawable绘制到View上
 */
public class MainActivity extends AppCompatActivity {

    public static final String PULLDOWN_LOTTIE_JSON = "pulldown_data.json";
    public static final String ROLLING_LOTTIE_JSON = "rolling_data.json";
    private static final String LOGO_LOTTIE_JSON = "LogoSmall.json";
    private static final String ANGRY_JSON = "angry.json";
    private LottieAnimationView animationView;
    private LottieAnimationView animationView2;
    private LottieAnimationView animationView3;
    private LottieAnimationView animationView4;
    private LottieAnimationView animationView5;
    private LottieAnimationView animationView6;

    private static final String TAG = MainActivity.class.getSimpleName();

    @RequiresApi(api = VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationView = (LottieAnimationView)findViewById(R.id.animation_view);
        animationView2 = (LottieAnimationView)findViewById(R.id.animation_view2);
        animationView3 = (LottieAnimationView)findViewById(R.id.animation_view3);
        animationView4 = (LottieAnimationView)findViewById(R.id.animation_view4);
        animationView5 = (LottieAnimationView)findViewById(R.id.animation_view5);
        animationView6 = (LottieAnimationView)findViewById(R.id.animation_view6);

        /**
         * 示例1:
         * Lottie可以通过ValueAnimator动态的设置动画值从0.0f到1.0f来实现动画的变化
         */
        animationView.setAnimation(PULLDOWN_LOTTIE_JSON);
        animationView.setProgress(0.0f);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f).setDuration(5000);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("yao", animation.getAnimatedValue() + "");
                animationView.loop(false);
                animationView.setProgress((Float)animation.getAnimatedValue());
            }
        });

        //animator.start();

        /**
         * 示例2:
         * Lottie可以ValueAnimator动态的设置动画，且当value值达到1.0时开始切换到另一个动画，并循环播放
         */

        animationView2.setAnimation(PULLDOWN_LOTTIE_JSON);
        //用于监听动画的开始结束状态
        animationView2.addAnimatorListener(new AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animation) {
                Logger.d(TAG + "--onAnimationCancel");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Logger.d(TAG + "--onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Logger.d(TAG + "--onAnimationRepeat");
            }

            @Override
            public void onAnimationStart(Animator animation) {
                Logger.d(TAG + "--onAnimationStart");
            }
        });

        //用于监听动画的更新状态
        animationView2.addAnimatorUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Logger.d(TAG + "--onAnimationUpdate:" + animation.getAnimatedValue());
            }
        });

        ValueAnimator animator2 = ValueAnimator.ofFloat(0f, 1.0f).setDuration(5000);
        animator2.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationView2.loop(false);
                //Logger.d("yao"+animation.getAnimatedValue()+"");
                animationView2.setProgress((Float)animation.getAnimatedValue());
                if ((float)animation.getAnimatedValue() == 1.0f) {
                    ////animationView2.setProgress(0.0f);
                    //animationView2.loop(true);
                    //animationView2.setAnimation(ROLLING_LOTTIE_JSON);
                    ////animationView2.setScale((float)1.0);
                    //animationView2.playAnimation();

                    animationView2.setAnimation(ROLLING_LOTTIE_JSON);


                    //Drawable drawable = animationView2.getDrawable();
                    //animationView2.setImageDrawable(drawable);
                    //animationView2.getDrawable().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_blue), Mode.SRC_ATOP);
                    //任何符合颜色过滤界面的类
                    final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.BLUE, Mode.SRC_IN);
                    //animationView2.addColorFilter(colorFilter);
                    animationView2.loop(true);
                    animationView2.playAnimation();
                }
            }
        });

        animator2.start();

        /**
         * 示例3：
         * 通过网络获取Json后加载(本地模拟)
         */

        ImageView imageView = new ImageView(this);
        imageView.setColorFilter(Color.parseColor("#ffffff"), Mode.SRC_IN);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(DataUtil.getLottieStringData());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Cancellable compositionCancellable = LottieComposition.Factory.fromJsonString(jsonObject.toString(),
            new OnCompositionLoadedListener() {
                @Override
                public void onCompositionLoaded(@Nullable LottieComposition composition) {
                    Logger.d("composition");
                    animationView3.setComposition(composition);
                    animationView3.playAnimation();
                }
            });

        findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                compositionCancellable.cancel();
                animationView3.cancelAnimation();
            }
        });

        /**
         * 示例4：
         * 监听Lottie动画的进度
         */
        animationView4.setAnimation(ANGRY_JSON);
        animationView4.loop(true);
        animationView4.playAnimation();
        animationView4.addAnimatorUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Logger.d(animation.getAnimatedValue().toString());
            }
        });

        /**
         * 示例5：
         * 添加颜色过滤器（包含整个动画、一个特定图层、图层上的特定内容）
         */
        animationView5.setAnimation(ROLLING_LOTTIE_JSON);
        //animationView5.loop(true);

        //任何符合颜色过滤界面的类
        final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.RED, Mode.LIGHTEN);
        //在整个视图中添加一个颜色过滤器
        //animationView5.addColorFilter(colorFilter);
        //animationView5.addColorFilter(colorFilter);
        animationView5.playAnimation();

        /**
         * 示例6：
         * 通过布局动态添加View
         */
        //LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400);
        //ProgressImageView progressImageView = new ProgressImageView(this);
        //progressImageView.setLayoutParams(layoutParams);
        //progressImageView.setScale(2.0f);
        //progressImageView.setBackgroundColor(Color.YELLOW);
        ////progressImageView.setImageResource(R.drawable.shape);
        //progressImageView.startDraw();
        //progressImageView.startAnim();
        //
        ////progressImageView.invalidate();
        //setContentView(progressImageView);

        /**
         * 示例7：(无效)
         * 通过LottieDrawable直接展示动画
         * LottieAnimationView内部就是使用的LottieDrawable
         */
        final LottieDrawable drawable = new LottieDrawable();
        LottieComposition.Factory.fromAssetFileName(this, LOGO_LOTTIE_JSON, new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition composition) {
                drawable.setComposition(composition);
                animationView6.setImageDrawable(drawable);
                animationView6.playAnimation();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //animationView2.pauseAnimation();
        animationView2.cancelAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //animationView2.resumeAnimation();
    }
}

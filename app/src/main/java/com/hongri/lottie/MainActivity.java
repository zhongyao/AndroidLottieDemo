package com.hongri.lottie;

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
import com.airbnb.lottie.Cancellable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.hongri.lottie.util.DataUtil;
import com.hongri.lottie.util.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author hongri
 */
public class MainActivity extends AppCompatActivity {

    private static final String PULLDOWN_LOTTIE_JSON = "pulldown_data_new.json";
    private static final String ROLLING_LOTTIE_JSON = "rolling_data_new.json";
    private static final String LOGO_LOTTIE_JSON = "LogoSmall.json";
    private LottieAnimationView animationView;
    private LottieAnimationView animationView2;
    private LottieAnimationView animationView3;
    private LottieAnimationView animationView4;
    private LottieAnimationView animationView5;

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

        /**
         * 示例1:
         * Lottie可以通过ValueAnimator动态的设置动画值从0.0f到1.0f来实现动画的变化
         */
        animationView.setAnimation(PULLDOWN_LOTTIE_JSON);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f).setDuration(5000);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("yao", animation.getAnimatedValue() + "");
                animationView.loop(false);
                animationView.setProgress((Float)animation.getAnimatedValue());
            }
        });

        animator.start();

        /**
         * 示例2:
         * Lottie可以ValueAnimator动态的设置动画，且当value值达到1.0时开始切换到另一个动画，并循环播放
         */

        animationView2.setAnimation(PULLDOWN_LOTTIE_JSON);
        ValueAnimator animator2 = ValueAnimator.ofFloat(0f, 1.0f).setDuration(5000);
        animator2.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationView2.loop(false);
                animationView2.setProgress((Float)animation.getAnimatedValue());
                if ((float)animation.getAnimatedValue() == 1.0f) {
                    animationView2.setAnimation(ROLLING_LOTTIE_JSON);
                    animationView2.setProgress(1.0f);
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
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(DataUtil.getLottieStringData());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Cancellable compositionCancellable = LottieComposition.Factory.fromJson(getResources(), jsonObject,
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
        animationView4.setAnimation(LOGO_LOTTIE_JSON);
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
        animationView5.loop(true);

        //任何符合颜色过滤界面的类
        final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.RED, Mode.LIGHTEN);
        //在整个视图中添加一个颜色过滤器
        animationView5.addColorFilter(colorFilter);
        animationView5.playAnimation();

    }
}

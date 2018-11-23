package com.hongri.lottie.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.airbnb.lottie.LottieAnimationView;
import com.hongri.lottie.R;
import com.hongri.lottie.util.Logger;

import static com.hongri.lottie.MainActivity.ROLLING_LOTTIE_JSON;

/**
 * @author zhongyao
 * @date 2018/11/23
 */

public class ProgressImageView extends LottieAnimationView {
    private Bitmap bitmap;
    private Paint paint;

    public ProgressImageView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logger.d("onDraw==");

        if (bitmap != null && paint != null) {
            Logger.d("onDraw-");
            canvas.drawBitmap(bitmap, 100, 100, paint);
        }

    }

    public void startAnim() {
        setAnimation(ROLLING_LOTTIE_JSON);
        loop(true);
        playAnimation();
    }

    public void startDraw() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.landscape);
        paint = new Paint();
        invalidate();
    }
}

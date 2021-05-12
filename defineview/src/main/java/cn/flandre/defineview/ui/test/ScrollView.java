package cn.flandre.defineview.ui.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

public class ScrollView extends View implements Runnable {
    private VelocityTracker velocityTracker;
    private int velocityX;
    private int lastX;
    private int allWidth;
    private int w, h;
    private Paint paint = new Paint();
    private Scroller scroller;

    public ScrollView(Context context) {
        super(context);
        init();
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        allWidth = 50 * 100;
        scroller = new Scroller(getContext(), null, false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(30);
        paint.setColor(0xffffff00);
        for (int i = 0; i < 100; i++) {
            if (isUseful(i)) {
                canvas.drawText(i + "", i * 50, getTop() + 100, paint);
            }
        }
    }

    private boolean isUseful(int i) {
        int scrollX = getScrollX();
        return i >= scrollX / 50 && i <= scrollX / 50 + w / 50 + 1;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (velocityTracker == null)
                    velocityTracker = VelocityTracker.obtain();
                velocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // 计算当前速度， 1000表示每秒像素数等
                velocityTracker.computeCurrentVelocity(1000, 8000);
                // 获取横向速度
                velocityX = (int) velocityTracker.getXVelocity();
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                    velocityTracker = null;
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stop();
                lastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int length = (int) (event.getX() - lastX);
                if (getScrollX() <= length)
                    scrollTo(0, 0);
                else if (getScrollX() - length + w >= allWidth)
                    scrollTo(allWidth - w, 0);
                else
                    scrollBy(-length, 0);

                lastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                start(getScrollX(), velocityX, allWidth);
                break;
        }
        return true;
    }

    private int preX;

    private void start(int startX, int v, int max) {
        stop();
        preX = startX;
        scroller.fling(startX, 0, v, 0, 0, max, 0, 0);
        postDelayed(this, 30);
    }

    @Override
    public void run() {
        if (!scroller.computeScrollOffset()) {
            return;
        }

        int x = scroller.getCurrX();
        int len = preX - x;
        if (len != 0) {
            if (getScrollX() + len >= allWidth - w) {
                scrollTo(allWidth - w, 0);
                return;
            } else if (getScrollX() + len <= 0) {
                scrollTo(0, 0);
                return;
            } else
                scrollBy(len, 0);
            preX = x;
        }
        postDelayed(this, 30);
    }

    private void stop() {
        if (!scroller.isFinished()) {
            scroller.abortAnimation();
        }
    }
}

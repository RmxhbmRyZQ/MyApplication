package cn.flandre.defineview.ui.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int a = MeasureSpec.getMode(widthMeasureSpec);
        int as = MeasureSpec.getSize(widthMeasureSpec);
        int b = MeasureSpec.getMode(heightMeasureSpec);
        int bs = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(as - 0x100, bs - 0x100);

    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l + 10, t + 10, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }
}

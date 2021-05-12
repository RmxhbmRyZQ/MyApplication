package cn.flandre.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.flandre.ui.R;

public class ProgressView extends View {
    private Drawable horizontalFade;
    private Drawable verticalFade;
    private float h;
    private float w;
    private Path progressPath;
    private Path wrapProgressPath;
    private Path framePath;
    private float centerY;
    private float radius;
    private float radiusTwo;
    private int lineLen;
    private Paint textPaint;
    private int progress = 40;
    private float padding = 10;
    private int textColor = 0x99000000;

    private void init() {
        horizontalFade = getContext().getDrawable(R.drawable.fade);
        verticalFade = getContext().getDrawable(R.drawable.fade_background);
        progressPath = new Path();
        wrapProgressPath = new Path();
        framePath = new Path();
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setStrokeWidth(3);
    }

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.progress);
        progress = ta.getInteger(R.styleable.progress_progress, 40);
        padding = ta.getDimension(R.styleable.progress_padding, 10);
        textColor = ta.getColor(R.styleable.progress_textColor, 0x99000000);
        ta.recycle();
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        int textSize = h / 5;
        lineLen = h / 10;
        this.h = h - (h / 10 * 3 + textSize + h / 20 + lineLen - (float) h / 2);

        textPaint.setTextSize(textSize);

        centerY = this.h / 2;
        radius = centerY;
        radiusTwo = radius / 5 * 3;
        framePath.reset();
        wrapProgressPath.reset();
        progressPath.reset();
        float newW = radius * 2 + (w - radius * 2) / 100 * progress;

        setPath(framePath, centerY, radius, 0, radiusTwo, w);

        setPath(wrapProgressPath, centerY, radius, padding, radiusTwo, w);
        setPath(progressPath, centerY, radius, padding, radiusTwo, newW);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        float newW = radius * 2 + (w - radius * 2) / 100 * progress;
        setPath(progressPath, centerY, radius, padding, radiusTwo, newW);
        postInvalidate();
    }

    private void setPath(Path path, float centerY, float radius, float padding, float radiusTwo, float w) {
        path.addCircle(centerY, centerY, radius - padding, Path.Direction.CW);
        path.addRect(centerY - radiusTwo + padding, centerY - radiusTwo + padding,
                w - radiusTwo, centerY + radiusTwo - padding, Path.Direction.CW);
        path.addCircle(w - radiusTwo, centerY, radiusTwo - padding, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float start = (int) Math.sqrt(radius * radius - radiusTwo * radiusTwo);
        float space = (w - start - radiusTwo - radius) / 10;
        float left;
        for (int i = 0; i <= 10; i++) {
            left = start + space * i + radius - 1.5f;
            canvas.drawLine(left, centerY + radiusTwo - padding, left, centerY + radiusTwo - padding + lineLen, textPaint);
            float v = textPaint.measureText(i * 10 + "");
            canvas.drawText(i * 10 + "", left - v / 2, centerY + radiusTwo + lineLen + h / 7, textPaint);
        }

        verticalFade.setBounds(0, 0, getWidth(), getHeight());
        horizontalFade.setBounds(0, 0, getWidth(), getHeight());
        canvas.clipPath(framePath);
        canvas.drawColor(0x11000000);
        canvas.clipPath(wrapProgressPath);
        verticalFade.draw(canvas);
        canvas.clipPath(progressPath);
        horizontalFade.draw(canvas);
    }
}

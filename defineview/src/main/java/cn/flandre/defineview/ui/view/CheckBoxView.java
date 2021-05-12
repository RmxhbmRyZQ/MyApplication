package cn.flandre.defineview.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.flandre.defineview.R;

public class CheckBoxView extends View {
    private int centerX;
    private int centerY;
    private int radius;
    private int big;
    private int progress = 1000;
    private boolean isCheck;
    private boolean isMoving = false;
    private int color = 0x333333, stroke = 10, unCheckColor = 0x33333333;
    private Paint paint;
    private Paint checkPaint;

    public CheckBoxView(Context context) {
        super(context);
        initPaint();
    }

    public CheckBoxView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CheckBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.checkBox);
        color = ta.getColor(R.styleable.checkBox_color, 0xffff00);
        unCheckColor = ta.getColor(R.styleable.checkBox_unCheckColor, 0x333333);
        stroke = (int) ta.getDimension(R.styleable.checkBox_stroke, 0x10);
        big = (int) ta.getDimension(R.styleable.checkBox_change, 0x10);
        ta.recycle();
        initPaint();
    }

    private void initPaint(){
        paint = new Paint();
        paint.setColor(color);
        checkPaint = new Paint();
    }

    public void setColor(int color) {
        this.color = color;
        postInvalidate();
    }

    public void setBig(int big) {
        radius += this.big - big;
        this.big = big;
        postInvalidate();
    }

    public void setUnCheckColor(int unCheckColor) {
        this.unCheckColor = unCheckColor;
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        radius = (centerX < centerY ? centerX : centerY) - 20;
        radius -= big;
    }

    public void setProgress(int p) {
        progress = p;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int halfWidth;
        int paintWidth;
        if (progress < 30) {
            // 画圆弧
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(stroke);
            paint.setColor(color);
            halfWidth = stroke / 2;
            canvas.drawArc(centerX - radius + halfWidth, centerY - radius + halfWidth, centerX + radius - halfWidth,
                    centerY + radius - halfWidth, 90, progress * 12 + 12, false, paint);
            return;
        }

        if (progress < 60) {
            // 画圆环
            paintWidth = stroke + (progress - 30) * ((radius - stroke) / 30);
            halfWidth = paintWidth / 2;
            paint.setStrokeWidth(paintWidth);
            canvas.drawArc(centerX - radius + halfWidth, centerY - radius + halfWidth, centerX + radius - halfWidth,
                    centerY + radius - halfWidth, 90, 360, false, paint);
            return;
        }

        if (progress < 80) {
            // 画圆变大
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(centerX, centerY, radius + (int) ((double)(progress - 60) / 20 * big), paint);
            int c = (int) (0xff * (((double) progress - 60) / 20));
            drawCheck((c << 24) + 0xffffff, canvas);
            return;
        }

        if (progress <= 100) {
            // 画圆缩小
            canvas.drawCircle(centerX, centerY, radius + (int)((double)Math.abs(progress - 100) / 20 * big), paint);
            drawCheck(0xffffffff, canvas);
            if (progress == 100) isMoving = false;
            return;
        }

        if (progress < 150){
            // 画虚线圆环
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(unCheckColor);
            paint.setStrokeWidth(stroke);
            halfWidth = stroke / 2;
            canvas.drawArc(centerX - radius + halfWidth, centerY - radius + halfWidth, centerX + radius - halfWidth,
                    centerY + radius - halfWidth, 90, (int)((progress - 100) * 7.2), false, paint);
            return;
        }

        if (progress <= 200){
            halfWidth = stroke / 2;
            canvas.drawArc(centerX - radius + halfWidth, centerY - radius + halfWidth, centerX + radius - halfWidth,
                    centerY + radius - halfWidth, 90, 360, false, paint);
            int c = (int) ((unCheckColor >> 24) * (((double) progress - 150) / 50));
            drawCheck((c << 24) + (unCheckColor & 0xffffff), canvas);
            if (progress == 200) isMoving = false;
            return;
        }

        // 未选择的画面
        paintWidth = stroke;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(paintWidth);
        paint.setColor(unCheckColor);
        canvas.drawCircle(centerX, centerY, radius, paint);
        drawCheck(unCheckColor, canvas);
    }

    private void drawCheck(int color, Canvas canvas){
        checkPaint.setColor(color);
        checkPaint.setStrokeWidth(stroke);
        checkPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(centerX - radius / 3, centerY);
        path.lineTo(centerX, centerY + radius / 3);
        path.lineTo(centerX + radius / 3, centerY - radius / 3);
        canvas.drawPath(path, checkPaint);
    }

    public void setCheck(boolean check) {
        if (isMoving) return;
        if (isCheck == check) return;
        isCheck = check;
        isMoving = true;
        if (check) {
            ObjectAnimator animator = ObjectAnimator.ofInt(this, "Progress", 0, 100);
            animator.setDuration(2000);
            animator.start();
            return;
        }

        // 取消选择

        ObjectAnimator animator = ObjectAnimator.ofInt(this, "Progress", 101, 200);
        animator.setDuration(1000);
        animator.start();
    }

    public boolean isCheck() {
        return isCheck;
    }
}

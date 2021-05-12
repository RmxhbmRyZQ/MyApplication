package cn.flandre.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.flandre.ui.R;
import cn.flandre.ui.bean.Point;
import cn.flandre.ui.utils.Convert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RateView extends View {
    private List<Point> points;

    private Paint linePaint;
    private Paint outCirclePaint;
    private Paint inCirclePaint;
    private Paint textPaint;
    private Paint topTextPaint;
    private Paint extraPaint;
    private int h;
    private int w;
    private float min;
    private float max;
    private float offsetX;
    private float offsetY;
    private float textSize;
    private float paddingTop;
    private float stroke;
    private float leftTextPadding;
    private float bottomTextPadding;
    private float topWritePadding;
    private String topString = "";

    public RateView(Context context) {
        super(context);
        initValue();
        paddingTop = textSize / 2;
        initPaint();
    }

    public RateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public RateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initValue();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RateView);
        offsetX = ta.getDimension(R.styleable.RateView_offsetX, offsetX);
        offsetY = ta.getDimension(R.styleable.RateView_offsetY, offsetY);
        textSize = ta.getDimension(R.styleable.RateView_textSize, textSize);
        leftTextPadding = ta.getDimension(R.styleable.RateView_leftTextPadding, leftTextPadding);
        bottomTextPadding = ta.getDimension(R.styleable.RateView_bottomTextPadding, bottomTextPadding);
        topWritePadding = ta.getDimension(R.styleable.RateView_topWritePadding, topWritePadding);
        String tmp = ta.getString(R.styleable.RateView_topText);
        topString = tmp != null ? tmp : topString;
        paddingTop = textSize / 2;
        ta.recycle();
        initPaint();
    }

    private void initValue(){
        offsetX = Convert.dip2px(getContext(), 20);
        offsetY = Convert.dip2px(getContext(), 20);
        textSize = Convert.sp2px(getContext(), 10);
        stroke = Convert.dip2px(getContext(), 2);
        leftTextPadding = Convert.dip2px(getContext(), 4);
        bottomTextPadding = Convert.dip2px(getContext(), 4);
        topWritePadding = Convert.dip2px(getContext(), 50);
    }

    private void initPaint() {
        linePaint = new Paint();
        linePaint.setStrokeWidth(5);
        linePaint.setColor(0x44ffffff);
        outCirclePaint = new Paint();
        outCirclePaint.setColor(0xffff5500);
        outCirclePaint.setStrokeWidth(5);
        inCirclePaint = new Paint();
        inCirclePaint.setColor(0xffffffff);
        textPaint = new Paint();
        int textColor = 0x99ffffff;
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        extraPaint = new Paint();
        extraPaint.setStrokeWidth(stroke);
        extraPaint.setColor(0xaaffffff);
        topTextPaint = new Paint();
        topTextPaint.setColor(0xffffffff);
        topTextPaint.setTextSize(topWritePadding / 2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawTop(canvas);
        drawLine(canvas);
        if (points == null) return;
        drawText(canvas);
        drawContent(canvas);
    }

    private void drawTop(Canvas canvas) {
        float radius = topWritePadding / 4;
        float y = topWritePadding / 2;
        float x = offsetX + y;
        canvas.drawLine(offsetX, y, offsetX + topWritePadding, y, outCirclePaint);
        canvas.drawCircle(x, y, radius, outCirclePaint);
        canvas.drawCircle(x, y, radius - radius / 4, inCirclePaint);

        canvas.drawText(topString, x + topWritePadding, radius * 2.8f, topTextPaint);
    }

    private void drawText(Canvas canvas) {
        int i;
        float h = this.h - offsetY - paddingTop - topWritePadding;
        float hStep = h / 5;
        float m = paddingTop * 2 + topWritePadding - 2.5f;
        float dataStep = (max - min) / 5;

        for (i = 0; i < 6; i++) {
            String v = String.valueOf(new BigDecimal(max - dataStep * i).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
            float len = textPaint.measureText(v);
            canvas.drawText(v, offsetX - len - leftTextPadding,  hStep * i + m, textPaint);
        }

        float space = (w - offsetX) / 5;
        h = this.h - offsetY + stroke + textSize + bottomTextPadding;
        m = space / 2 + offsetX;
        for (i = 0;i<5;i++){
            float x = m + space * i;
            String v = points.get(i).getDate().substring(0, 7);
            float len = textPaint.measureText(v);
            canvas.drawText(v, x - len / 2, h, textPaint);
        }
    }

    private void drawLine(Canvas canvas) {
        float hStep = (this.h - offsetY - paddingTop - topWritePadding) / 5;
        float y;
        float top = paddingTop + topWritePadding;
        for (int i = 0; i < 5; i++) {
            y =  hStep * i + top;
            canvas.drawLine(offsetX, y, w, y, linePaint);
        }
        y = this.h - offsetY + stroke / 2;
        canvas.drawLine(offsetX, y, w, y, extraPaint);
    }

    private void drawContent(Canvas canvas) {
        float x2 = 0, y2 = 0;
        float h = this.h - offsetY - paddingTop - topWritePadding;
        float space = (w - offsetX) / 5;
        float hSpace = space / 2;
        float top = paddingTop + topWritePadding;
        float len = max - min;

        for (int i = 0; i + 1 < points.size(); i++) {
            float y = h - (Float.parseFloat(points.get(i).getIndex()) - min) / len * h + top;
            y2 = h - (Float.parseFloat(points.get(i + 1).getIndex()) - min) / len * h + top;
            float x = hSpace + space * i + offsetX;
            x2 = x + space;
            canvas.drawLine(x, y, x2, y2, outCirclePaint);
            drawCircle(x, y, canvas);
        }
        if (x2 != 0 && y2 != 0)
            drawCircle(x2, y2, canvas);
    }

    private void drawCircle(float x, float y, Canvas canvas) {
        canvas.drawCircle(x, y, (float) 20, outCirclePaint);
        canvas.drawCircle(x, y, (float) 10, inCirclePaint);
    }

    public void setData(LinkedHashMap<String, String> data) {
        float v, max = -10000, min = 10000;
        for (String value : data.values()) {
            v = Float.parseFloat(value);
            if (v > max) max = v;
            if (v < min) min = v;
        }
        max = getNumber(max, true);
        min = getNumber(min, false);
        List<Point> points = new ArrayList<>();
        for (Map.Entry<String, String> stringStringEntry : data.entrySet()) {
            points.add(new Point(stringStringEntry.getKey(), stringStringEntry.getValue()));
        }
        this.points = points;
        this.min = min;
        this.max = max;
        postInvalidate();
    }

    /**
     * 对最高位进行取整
     * 3.145 -> 4 if the up is true
     * 3.145 -> 3 if the up is false
     *
     * @param up: 向上取整还是向下取整
     */
    private float getNumber(float n, boolean up) {
        if (n == 0) return up ? 1 : 0;
        String s = String.valueOf(n);
        int sign = 1;
        int i, j = 0;
        int start = 0;
        if (s.charAt(0) == '-'){
            sign = -1;
            start = 1;
        }
        int extra = up ? 1 : 0;
        if (s.charAt(0) == '0') {
            for (i = start; i < s.length() && s.charAt(i) != '.'; i++) ;
            for (++i; i < s.length() && s.charAt(i) == '0'; i++) j++;
            return (float) ((s.charAt(i) != '0' ? s.charAt(i) - '0' + extra : 0) * Math.pow(10, -j - 1)) * sign;
        } else {
            for (i = start; i < s.length() && s.charAt(i) != '.'; i++) j++;
            return (float) ((s.charAt(start) - '0' + extra) * Math.pow(10, j - 1)) * sign;
        }
    }
}

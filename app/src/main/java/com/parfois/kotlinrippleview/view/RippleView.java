package com.parfois.kotlinrippleview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.parfois.kotlinrippleview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parfoismeng on 2017/5/31.
 */

public class RippleView extends View {
    private Paint paint;

    private boolean isStarting;// 是否运行
    private int centerRadius;// 中心圆半径
    private int circleSum;// 同心圆总数


    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttr(context, attrs);
    }

    public RippleView(Context context) {
        super(context);
        init();
    }

    private void init() {
        isStarting = false;

        paint = new Paint();
        list.add(new Round(0, 255, Color.BLUE));
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ripple);
        centerRadius = a.getDimensionPixelSize(R.styleable.ripple_centerRadius, 50);
        circleSum = a.getInteger(R.styleable.ripple_circleSum, 5);
    }

    private List<Round> list = new ArrayList<>();

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setTextSize(24);
        canvas.drawText("" + list.size(), getWidth() / 2, 50, paint);

        setBackgroundColor(Color.TRANSPARENT);// 颜色：完全透明

        for (int i = 0; i < list.size(); i++) {
            paint.setColor(list.get(i).color);
            paint.setAlpha(list.get(i).alpha);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, list.get(i).radius + centerRadius, paint);

            if (isStarting && list.get(i).alpha > 0 && list.get(i).radius < getWidth()) {
                list.get(i).alpha--;
                list.get(i).radius++;
            }
        }

        if (isStarting && list.get(list.size() - 1).radius == getWidth() / 2 / circleSum) {
            list.add(new Round(0, 255, Color.BLUE));
        }

        if (isStarting && list.size() > 10) {
            list.remove(0);
        }

        invalidate();
    }

    // 执行动画
    public void toggle() {
        isStarting = !isStarting;
    }

    // 判断动画在不在执行
    public boolean isStarting() {
        return isStarting;
    }

    private class Round {
        int radius;
        int alpha;
        int color;

        public Round(int radius, int alpha, int color) {
            this.radius = radius;
            this.alpha = alpha;
            this.color = color;
        }
    }
}
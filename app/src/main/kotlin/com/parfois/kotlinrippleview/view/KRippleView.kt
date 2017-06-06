package com.parfois.kotlinrippleview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.parfois.kotlinrippleview.R
import org.jetbrains.anko.sp

/**
 * Created by parfoismeng on 2017/5/31.
 */
class KRippleView : View {
    private val paint: Paint = Paint()

    private var isStarting: Boolean = false// 是否运行
    private var centerRadius: Int = 0// 中心圆半径
    private var circleSum: Int = 0// 同心圆总数

    private val list = arrayListOf<Round>()
    private val colors = arrayListOf(Color.BLACK, Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA/*, Color.TRANSPARENT*/)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
        initAttr(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
        initAttr(context, attrs)
    }

    private fun init() {
        paint.isAntiAlias = true
        paint.textSize = sp(24).toFloat()
        paint.textAlign = Paint.Align.CENTER

        list.add(Round(0, 255 * 2, colors[random(colors.size - 1, 0)]))
    }

    private fun initAttr(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ripple)
        centerRadius = a.getDimensionPixelSize(R.styleable.ripple_centerRadius, 50)
        circleSum = a.getInteger(R.styleable.ripple_circleSum, 5)
    }

    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        setBackgroundColor(Color.TRANSPARENT)// 颜色：完全透明

        for (i in list.indices) {
//            paint.color = list[i].color
            paint.color = ContextCompat.getColor(context, R.color.material_deep_teal_200)
            paint.alpha = list[i].alpha / 2
            canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (list[i].radius + centerRadius).toFloat(), paint)

            if (isStarting && list[i].alpha > 0 && list[i].radius < width) {
                list[i].alpha--
                list[i].radius++
            }
        }

        if (isStarting && list[list.size - 1].radius == width / 2 / circleSum) {
            list.add(Round(0, 255 * 2, colors[random(colors.size - 1, 0)]))
        }

//        if (isStarting && list[0].radius > hypotenuse(width.toDouble(), height.toDouble()) / 2) {
//            list.removeAt(0)
//        }

        if (isStarting && list.size > 10) {
            list.removeAt(0)
        }

//        paint.color = Color.WHITE
//        canvas.drawRect((width / 2 - 150).toFloat(), 0f, (width / 2 + 150).toFloat(), 100f, paint)
        paint.color = Color.BLACK
        canvas.drawText("--- " + list.size + " ---", (width / 2).toFloat(), 80f, paint)

        invalidate()
    }

    // 开关动画
    fun toggle() {
        isStarting = !isStarting
    }

    fun diff() {
        list.add(Round(0, 255 * 2, colors[random(colors.size - 1, 0)]))
    }

    // 动画是否已打开
    fun isStarting(): Boolean {
        return isStarting
    }

    private class Round(var radius: Int, var alpha: Int, var color: Int)
}
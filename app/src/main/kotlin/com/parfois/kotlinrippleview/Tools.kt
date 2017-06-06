package com.parfois.kotlinrippleview.view

/**
 * Created by parfoismeng on 2017/5/31.
 */
fun random(max: Int, min: Int): Int {
    return (min + Math.random() * (max - min + 1)).toInt()
}

fun hypotenuse(a: Double, b: Double): Double {
    return Math.sqrt(a * a + b * b)
}
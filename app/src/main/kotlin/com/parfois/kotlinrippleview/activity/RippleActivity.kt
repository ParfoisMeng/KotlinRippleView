package com.parfois.kotlinrippleview.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.parfois.kotlinrippleview.R
import kotlinx.android.synthetic.main.activity_ripple.*

class RippleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ripple)

        ripple.setOnClickListener {
//            if (!ripple.isStarting()) {
//                ripple.toggle()
//            } else {
//                ripple.diff()
//            }
            ripple.toggle()
        }
    }
}

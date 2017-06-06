package com.parfois.kotlinrippleview.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.parfois.hellokotlinandroid.adapter.MyAdapter
import com.parfois.kotlinrippleview.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class KMainActivity : AppCompatActivity() {
    val list = arrayListOf<String>()
    val adapter = MyAdapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.addAll(arrayListOf("111", "222", "333"))
        lv.adapter = adapter

        swipe.onRefresh {
            if (!swipe.isRefreshing) swipe.isRefreshing = true

            list.addAll(0, arrayListOf("1-" + (Math.random() * 100).toInt(), "2-" + (Math.random() * 100).toInt(), "3-" + (Math.random() * 100).toInt()))

            swipe.postDelayed({
                adapter.notifyDataSetChanged()
                if (swipe.isRefreshing) swipe.isRefreshing = false
            }, 1000)
        }
        lv.setOnItemClickListener { parent, view, position, id -> startActivity<RippleActivity>() }
    }

}

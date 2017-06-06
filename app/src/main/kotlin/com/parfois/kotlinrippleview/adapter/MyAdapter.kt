package com.parfois.hellokotlinandroid.adapter

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.jetbrains.anko.*
import java.util.*

/**
 * Created by parfoismeng on 2017/5/27.
 */
class MyAdapter(val list: ArrayList<String>, val mContext: Context) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return with(mContext) {
            verticalLayout {
                padding = dip(15)
                gravity = Gravity.CENTER
                textView(list[position]) {
                    gravity = Gravity.CENTER
                    textSize = sp(14).toFloat()
                }
            }
        }
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}
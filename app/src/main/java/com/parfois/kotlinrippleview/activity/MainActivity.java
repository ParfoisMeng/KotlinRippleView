package com.parfois.kotlinrippleview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parfois.hellokotlinandroid.adapter.MyAdapter;
import com.parfois.kotlinrippleview.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        adapter = new MyAdapter(list, this);

        list.add("111");
        list.add("222");
        list.add("333");

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, RippleActivity.class));
            }
        });

        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!swipe.isRefreshing()) swipe.setRefreshing(true);

                list.add("1-" + (int) (Math.random() * 100));
                list.add("2-" + (int) (Math.random() * 100));
                list.add("3-" + (int) (Math.random() * 100));

                swipe.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        if (swipe.isRefreshing()) swipe.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }
}

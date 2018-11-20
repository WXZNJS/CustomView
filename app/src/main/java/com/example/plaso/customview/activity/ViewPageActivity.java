package com.example.plaso.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.plaso.customview.R;
import com.example.plaso.customview.adapter.MyPageAdapter;
import com.example.plaso.customview.view.AlphaPageTransformer;

import java.util.ArrayList;

/**
 * Created by zhhang on 2018/9/15.
 */

public class ViewPageActivity extends Activity{
    ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_adapter_activity);
        initData();
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(true,new AlphaPageTransformer());
        viewPager.setAdapter(new MyPageAdapter(list,this));
        viewPager.setPageMargin(10);
    }

    public void initData(){
        list = new ArrayList<>();
        list.add(R.drawable.img0);
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img3);
        list.add(R.drawable.img4);
    }
}

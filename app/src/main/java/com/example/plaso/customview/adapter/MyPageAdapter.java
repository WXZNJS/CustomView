package com.example.plaso.customview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plaso.customview.R;

import java.util.ArrayList;

/**
 * Created by zhhang on 2018/9/15.
 */

public class MyPageAdapter extends PagerAdapter {
    ArrayList<Integer> mData = new ArrayList<>();
    Context mContext;
    public MyPageAdapter(ArrayList<Integer> data,Context context){
        mData = data;
        mContext= context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.page_adapter_layout, container,false);
        TextView textView = view.findViewById(R.id.text_item);
        textView.setText(String.valueOf(position));
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

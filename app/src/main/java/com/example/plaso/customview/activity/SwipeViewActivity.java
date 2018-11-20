package com.example.plaso.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.plaso.customview.R;
import com.example.plaso.customview.view.SwipeLayout;

/**
 * Created by zhhang on 2018/9/7.
 */

public class SwipeViewActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_view_layout);
        initView();
    }

    public void initView(){
        SwipeLayout swipeLayout = findViewById(R.id.swipe_view);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left,findViewById(R.id.bottom_wrapper));
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right,findViewById(R.id.bottom_wrapper_2));
        findViewById(R.id.star2).setOnClickListener(this);
        findViewById(R.id.trash2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.star2:
                Toast.makeText(this,"click star2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.trash2:
                Toast.makeText(this,"click trash",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

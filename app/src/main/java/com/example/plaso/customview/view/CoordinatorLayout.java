package com.example.plaso.customview.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.plaso.customview.R;
import com.example.plaso.customview.adapter.CoordinatorAdapter;

/**
 * Created by zhhang on 2018/8/22.
 */

public class CoordinatorLayout extends Activity{
    RecyclerView recyclerView;
    Toolbar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_layout);
        initView();
    }

    public void initView(){
        recyclerView = findViewById(R.id.recycler);
        toolBar = findViewById(R.id.toolbar);
        CoordinatorAdapter adapter = new CoordinatorAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}

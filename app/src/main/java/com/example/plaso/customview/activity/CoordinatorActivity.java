package com.example.plaso.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.plaso.customview.R;
import com.example.plaso.customview.adapter.CoordinatorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhhang on 2018/8/22.
 */

public class CoordinatorActivity extends Activity{
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    Toolbar toolBar;
    CoordinatorAdapter adapter;
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_layout);
        initView();
    }

    public void initView(){
        for(int i=0;i<10;i++){
            list.add(i);
        }
        recyclerView = findViewById(R.id.recycler);
        toolBar = findViewById(R.id.toolbar);
        refreshLayout = findViewById(R.id.refresh_layout);
        adapter = new CoordinatorAdapter(this,list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        setLoadMoreListener(manager);
        setRefreshListener();
    }

    private void setRefreshListener(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(refreshLayout.isRefreshing()){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                            refreshData();
                        }
                    },2000);
                }

            }
        });
    }

    private void setLoadMoreListener(final LinearLayoutManager layoutManager){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition == adapter.getItemCount()-1 && recyclerView.SCROLL_STATE_IDLE == newState){
                    loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    boolean isLoading = false;
    private void loadMore(){
        if(!isLoading){
            isLoading = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(adapter.getData().size()==50){
                        adapter.setNoNoreData(true);
                    }else {
                        List<Integer> list = new ArrayList<>();
                        for(int i=0;i<10;i++){
                            list.add(i);
                        }
                        adapter.setData(list);
                    }
                    isLoading = false;
                }
            },1500);
        }

    }

    private void refreshData(){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        adapter.refreshData();
        adapter.setNoNoreData(false);
        adapter.setData(list);
    }
}

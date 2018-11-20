package com.example.plaso.customview.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;

/**
 * Created by zhhang on 2018/9/7.
 */

public class SwipeListActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler.sendEmptyMessageDelayed(1,1);
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.toString();
    }

    @SuppressLint("handlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }
    };

}

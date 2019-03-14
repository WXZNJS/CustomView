package com.example.plaso.customview;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;

import com.example.plaso.customview.activity.DrawActivity;
import com.example.plaso.customview.activity.FaceDetectActivity;
import com.example.plaso.customview.activity.SwipeViewActivity;
import com.example.plaso.customview.activity.TokePhotoActivity;
import com.example.plaso.customview.activity.ViewPageActivity;
import com.example.plaso.customview.activity.WheelPickerActivity;
import com.example.plaso.customview.dataBean.PieData;
import com.example.plaso.customview.activity.CoordinatorActivity;
import com.example.plaso.customview.view.PieView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends Activity implements View.OnClickListener{
    PieView pieView;
    ProgressBar progressBar;
    MyView myView;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zh","Main_onCreate");

        setContentView(R.layout.activity_main);
        findViewById(R.id.show_coordinator).setOnClickListener(this);
        findViewById(R.id.drawing_view).setOnClickListener(this);
        findViewById(R.id.swipe_layout).setOnClickListener(this);
        findViewById(R.id.viewPager_layout).setOnClickListener(this);
        findViewById(R.id.wheel_picker).setOnClickListener(this);
        findViewById(R.id.image_picker).setOnClickListener(this);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.bind).setOnClickListener(this);
        findViewById(R.id.unbind).setOnClickListener(this);
        myView = findViewById(R.id.myView);
        initBroadCast();

        myView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                    myView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Log.d("zh","viewWidth_"+myView.getWidth());
            }
        });

        myView.post(new Runnable() {
            @Override
            public void run() {
                Log.d("zh","postViewWidth_"+myView.getWidth());
            }
        });
    }

    public void initBroadCast(){
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(action.equals(MyIntentService.UNBIND_SERVICE)){
                    unbindService(connection);
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(MyIntentService.UNBIND_SERVICE);
        registerReceiver(receiver,filter);
    }



    public void initPie(){
        ArrayList<PieData> pieData = new ArrayList<>();
        pieData.add(new PieData(20));
        pieData.add(new PieData(30));
        pieView.setData(pieData);
    }


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_coordinator:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CoordinatorActivity.class);
                startActivity(intent);
                break;
            case R.id.drawing_view:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, DrawActivity.class);
                startActivity(intent1);
                break;
            case R.id.swipe_layout:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, SwipeViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.viewPager_layout:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, ViewPageActivity.class);
                startActivity(intent3);
                break;
            case R.id.wheel_picker:
                Intent intent4 = new Intent();
                intent4.setClass(MainActivity.this, WheelPickerActivity.class);
                startActivity(intent4);
                break;
            case R.id.start:
                Intent intent5 = new Intent(this, MyService.class);
                startService(intent5);
                break;
            case R.id.stop:
                Intent intent6 = new Intent(this, MyService.class);
                stopService(intent6);
                break;
            case R.id.bind:
               /* Intent intent7 = new Intent(this, MyService.class);
                bindService(intent7,connection,BIND_AUTO_CREATE);*/
               myView.invalidate();
                break;
            case R.id.unbind:
                /*Intent intent8 = new Intent(this, MyIntentService.class);
                bindService(intent8,connection,BIND_AUTO_CREATE);
                startService(intent8);*/
//                startActivity(new Intent(MainActivity.this,CustomProgressActivity.class));
                myView.requestLayout();
                break;
            case R.id.image_picker:
                startActivity(new Intent(this,FaceDetectActivity.class));
                break;

        }
    }

    //非静态内部类对外部类的引用改为弱引用。
    public class MyThread extends Thread{
        private WeakReference<MainActivity> weakActivity;
        public MyThread(MainActivity activity){
            weakActivity = new WeakReference<>(activity);
        }
    }

    LinkedList linkedList = new LinkedList();
    public void test(){
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("zh","Main_onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("zh","Main_onStart");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d("zh","main_postCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zh","Main_onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("zh","onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("zh","Main_onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("zh","Main_onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Log.d("zh","Main_onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("zh","onConfigChanged");
    }

    //activity布局改动时会回调此方法。setContentView
    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }
}

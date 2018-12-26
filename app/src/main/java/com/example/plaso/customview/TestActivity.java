package com.example.plaso.customview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plaso.customview.observer.Persion;
import com.example.plaso.customview.observer.ZhHangSubject;

public class TestActivity extends Activity implements View.OnClickListener{

    ZhHangSubject zhHangSubject;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zh","TestActivity_onCreate");
        setContentView(R.layout.test_layout);
        initView();
        initEvent();
    }

    public void initView(){
        findViewById(R.id.xiaoming_focus).setOnClickListener(this);
        findViewById(R.id.xiaoliang_focus).setOnClickListener(this);
        findViewById(R.id.xiaoqin_focus).setOnClickListener(this);
        findViewById(R.id.delete_focus).setOnClickListener(this);
        findViewById(R.id.notify_focus).setOnClickListener(this);
        textView = findViewById(R.id.show_text);
    }

    public void initEvent(){
        zhHangSubject = new ZhHangSubject();
    }

    Persion xiaoming;
    Persion xiaoliang;
    Persion xiaoqin;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xiaoliang_focus:
                if(null==xiaoliang){
                    xiaoliang = new Persion("小亮");
                    zhHangSubject.attach(xiaoliang);
                    Toast.makeText(this,"小亮关注了我",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"不能重复关注",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xiaoming_focus:
                if(null==xiaoming){
                    xiaoming = new Persion("小明");
                    zhHangSubject.attach(xiaoming);
                    Toast.makeText(this,"小明关注了我",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"不能重复关注",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xiaoqin_focus:
                if(null==xiaoqin){
                    xiaoqin = new Persion("小琴");
                    zhHangSubject.attach(xiaoqin);
                    Toast.makeText(this,"小琴关注了我",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"不能重复关注",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.notify_focus:
                zhHangSubject.notify("我发布了一条新消息",textView);
                break;
            case R.id.delete_focus:

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("zh","TestActivity_onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zh","TestActivity___onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("zh","TestActivity_onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("zh","TestActivity_onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("zh","TestActivity_onDestroy");
    }
}

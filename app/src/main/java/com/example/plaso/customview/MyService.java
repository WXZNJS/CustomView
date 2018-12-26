package com.example.plaso.customview;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.LinkedList;
import java.util.Stack;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("zh","onCreat");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("zh","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("zh","onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("zh","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("zh","onDestroy");
        super.onDestroy();
    }

    public void outer(){

    }

    static class Inner{
        private void OutDo(){
            new MyService().outer();
        }

        public void inner(){

        }

        public void userOuter(MyService myService){

        }
    }




}





package com.example.plaso.customview;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {
    public static final String UNBIND_SERVICE="unbind_service";
   public MyIntentService(){
       super("MyIntentService");

   }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("zh","onCreate");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("zh","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       Log.d("zh","onBind");
       return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
       Log.d("zh","onUnBind");
       return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("zh","onDestroy");
   }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("zh","onHandlerIntent");
        sendBroadcast(new Intent(UNBIND_SERVICE));
    }
}

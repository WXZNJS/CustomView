package com.example.plaso.customview.util;

import android.util.Log;

public class LogUtil {

    public static void LongLog(String resp){
        if(resp.length() > 4000) {
            for(int i=0;i<resp.length();i+=4000){
                if(i+4000<resp.length())
                    Log.d("zh","faceDetect_"+resp.substring(i,i+4000));
                else
                    Log.d("zh","faceDetect_"+resp.substring(i,resp.length()));
            }
        }
    };
}

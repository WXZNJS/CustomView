package com.example.plaso.customview.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class SystemUtil {

    public static int[] getScreenWH(Activity mActivity){
        WindowManager manager = mActivity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        return new int[]{width,height};
    }
}

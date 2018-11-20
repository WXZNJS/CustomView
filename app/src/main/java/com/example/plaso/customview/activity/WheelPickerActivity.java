package com.example.plaso.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.plaso.customview.R;
import com.example.plaso.customview.view.WheelPicker;

/**
 * Created by zhhang on 2018/10/9.
 */

public class WheelPickerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_picker_layout);
        WheelPicker picker = (WheelPicker) findViewById(R.id.wheel_picker);
        picker.setIndicator(true);
    }
}

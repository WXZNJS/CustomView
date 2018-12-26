package com.example.plaso.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.plaso.customview.R;
import com.example.plaso.customview.view.CustomCircleProgress;

public class CustomProgressActivity extends Activity implements View.OnClickListener{

    CustomCircleProgress circle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_circle_progress_layout);
        circle = findViewById(R.id.progress);
        findViewById(R.id.restart).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.restart:
                circle.setProgress(60);
                break;
        }
    }
}

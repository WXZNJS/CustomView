package com.example.plaso.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.plaso.customview.R;
import com.example.plaso.customview.view.CheckImage;
import com.example.plaso.customview.view.DrawView;

import java.util.ArrayList;

/**
 * Created by zhhang on 2018/8/29.
 */

public class DrawActivity extends Activity implements View.OnClickListener{
    DrawView drawView;
    CheckImage paint;
    CheckImage eraser;
    CheckImage clean;
    boolean checked = true;
    boolean unChecked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.drawing_layout);
        initView();
    }

    private void initView(){
        paint = findViewById(R.id.paint);
        eraser = findViewById(R.id.eraser);
        drawView = findViewById(R.id.drawing_view);
        clean = findViewById(R.id.clean);
        paint.setOnClickListener(this);
        eraser.setOnClickListener(this);
        clean.setOnClickListener(this);
        paint.setChecked(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.paint:
                drawView.initPaint();
                setChecked(R.id.paint);
                break;
            case R.id.eraser:
                drawView.initEraser();
                setChecked(R.id.eraser);
                break;
            case R.id.clean:
                drawView.clean();
                break;
        }
    }

    public void setChecked(int id){
        paint.setChecked(id ==R.id.paint ? checked : unChecked);
        eraser.setChecked(id == R.id.eraser ? checked : unChecked);
    }


}

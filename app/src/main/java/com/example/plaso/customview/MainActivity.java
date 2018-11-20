package com.example.plaso.customview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;

import com.example.plaso.customview.activity.DrawActivity;
import com.example.plaso.customview.activity.SwipeViewActivity;
import com.example.plaso.customview.activity.ViewPageActivity;
import com.example.plaso.customview.activity.WheelPickerActivity;
import com.example.plaso.customview.dataBean.PieData;
import com.example.plaso.customview.view.CoordinatorLayout;
import com.example.plaso.customview.view.PieView;
import com.example.plaso.customview.view.WheelPicker;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{
    PieView pieView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zh","onCreat");
        setContentView(R.layout.activity_main);
        findViewById(R.id.show_coordinator).setOnClickListener(this);
        findViewById(R.id.drawing_view).setOnClickListener(this);
        findViewById(R.id.swipe_layout).setOnClickListener(this);
        findViewById(R.id.viewPager_layout).setOnClickListener(this);
        findViewById(R.id.wheel_picker).setOnClickListener(this);

    }



    public void initPie(){
        ArrayList<PieData> pieData = new ArrayList<>();
        pieData.add(new PieData(20));
        pieData.add(new PieData(30));
        pieView.setData(pieData);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_coordinator:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CoordinatorLayout.class);
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
        }
    }

}

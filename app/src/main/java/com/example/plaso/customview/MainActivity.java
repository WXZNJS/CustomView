package com.example.plaso.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.plaso.customview.view.CheckView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CheckView checkView;
    PieView pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieView = (PieView) findViewById(R.id.pie_view);
        checkView.setOnClickListener(this);
        initPie();
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

        }
    }
}

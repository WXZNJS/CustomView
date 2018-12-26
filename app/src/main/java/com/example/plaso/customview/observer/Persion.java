package com.example.plaso.customview.observer;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class Persion implements Observer{

    String name;
    public Persion(String name){
        this.name = name;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updata(String msg, TextView tv) {
        tv.setText(tv.getText()+name+" :"+msg+"\n");
    }
}

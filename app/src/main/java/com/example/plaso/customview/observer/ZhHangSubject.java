package com.example.plaso.customview.observer;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ZhHangSubject implements Subject{
    private List<Observer> mPersionList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        mPersionList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        mPersionList.remove(observer);
    }

    @Override
    public void notify(String message, TextView tv) {
        for(Observer observer:mPersionList){
            observer.updata(message,tv);
        }
    }
}

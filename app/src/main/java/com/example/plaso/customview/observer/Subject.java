package com.example.plaso.customview.observer;

import android.widget.TextView;

public interface Subject {

    /**
     * 增加观察者
     * @param observer
     */
    void attach(Observer observer);

    /**
     * 删除观察者
     * @param observer
     */
    void detach(Observer observer);

    /**
     * 通知观察者
     * @param message
     * @param tv
     */
    void notify(String message, TextView tv);
}

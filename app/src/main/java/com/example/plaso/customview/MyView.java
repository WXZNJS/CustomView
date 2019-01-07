package com.example.plaso.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {


    public MyView(Context context) {
        super(context);
        Log.d("zh","view_onCreate1");
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("zh","view_onCreate2");
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("zh","view_onCreate3");
    }

    //从xml加载完成后调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("zh","view______onFinishInflate");
    }

    //将view绑定到activity所在的window,随后开始进行所有view的绘制
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("zh","view______onAttachedToWindow");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("zh","view______onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("zh","view______onLayout");
    }

    //判断view是否获得焦点，可以判断view进出前后台
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.d("zh","view______onWindowFocusChanged:"+hasWindowFocus);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String a="1";
        String b="2";
        a.equals(b);
        Log.d("zh","view______onDraw");
    }


    //activity销毁后，view会从window抽离，此时view销毁。
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("zh","view______onDetachedFromWindoe");
    }

}

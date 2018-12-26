package com.example.plaso.customview.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.plaso.customview.R;
import com.example.plaso.customview.util.DimenUtil;

public class CustomCircleProgress extends View {
    int bgColor;
    int progressColor;
    float maxProgress;
    float progress;
    float radius;
    float progressWidth;
    Paint mPaint;
    int textColor;
    float textSize;

    public CustomCircleProgress(Context context) {
        this(context,null);
    }

    public CustomCircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CustomCircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCircleProgress, defStyleAttr, 0);
        bgColor = a.getColor(R.styleable.CustomCircleProgress_bg_color, context.getResources().getColor(R.color.grey));
        progressColor = a.getColor(R.styleable.CustomCircleProgress_progress_color, context.getResources().getColor(R.color.green));
        textColor = a.getColor(R.styleable.CustomCircleProgress_progress_text_color, context.getResources().getColor(R.color.text_black));
        textSize = a.getDimension(R.styleable.CustomCircleProgress_progress_text_size, DimenUtil.dp2px(context, 14));
        maxProgress = a.getFloat(R.styleable.CustomCircleProgress_max_progress,100);
        progress = a.getFloat(R.styleable.CustomCircleProgress_progress,0);

        radius = a.getDimension(R.styleable.CustomCircleProgress_radius,DimenUtil.dp2px(context,40));
        progressWidth = a.getDimension(R.styleable.CustomCircleProgress_progress_width,DimenUtil.dp2px(context,4));
        a.recycle();

        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float width,height;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        if(MeasureSpec.EXACTLY == mode){
            width=size;
            height = size;
        }else {
            width = radius*2+progressWidth;
            height = radius*2+progressWidth;
        }
        setMeasuredDimension((int)width,(int)height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆环
        int circleCenter = getWidth()/2;
        mPaint.setColor(bgColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progressWidth);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(circleCenter,circleCenter,radius,mPaint);

        //画圆弧
        mPaint.setColor(progressColor);
        RectF rectF = new RectF(circleCenter - radius, circleCenter - radius, circleCenter + radius, circleCenter + radius);
        canvas.drawArc(rectF,0,360*(progress/maxProgress),false,mPaint);

        //画圆环内文字
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(0);
        String progressText = getProgressText();
        int textWidth = (int)mPaint.measureText(progressText, 0, progressText.length());
        Rect rect = new Rect();
        mPaint.getTextBounds(progressText,0,1,rect);
        int textHeight = rect.height();
        canvas.drawText(getProgressText(),circleCenter-textWidth/2,circleCenter+textHeight/2,mPaint);

    }

    private String getProgressText(){
        return (int)((progress/maxProgress)*100)+"%";
    }

    public void setProgress(float progress){
        if(progress<0){
            throw new IllegalArgumentException("progress can't less than 0");
        }
        if(progress>maxProgress){
            this.progress = maxProgress;
        }

        startAnim(progress);
    }

    public float getProgress(){
        return progress;
    }

    private void startAnim(float startProgress){
        ValueAnimator animator = ObjectAnimator.ofFloat(0, startProgress);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                CustomCircleProgress.this.progress = (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });

        animator.setStartDelay(500);
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
}

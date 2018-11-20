package com.example.plaso.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.plaso.customview.R;

/**
 * Created by zhhang on 2018/8/29.
 */

public class DrawView extends View {
    public static final float TOUCH_TOLERANCE= 4;
    Context mContext;
    Paint mPaint;
    Paint mEraserPaint;
    Canvas mCanvas;
    Path mPath;
    Bitmap mBitmap;
    Paint mBitmapPaint;
    int width;
    int height;
    private float mX, mY;
    public boolean drawMode = true;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mPaint = new Paint();
        mEraserPaint = new Paint();
        initPaint();
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        width = manager.getDefaultDisplay().getWidth();
        height = manager.getDefaultDisplay().getHeight();
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void initPaint(){
        drawMode = true;
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(mContext.getResources().getColor(R.color.red));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    }

    public void initEraser(){
        drawMode = false;
        mEraserPaint.setColor(Color.WHITE);
        mEraserPaint.setStyle(Paint.Style.STROKE);
        mEraserPaint.setStrokeWidth(80);
//        mEraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

    }

    public void clean(){
        mBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);
        invalidate();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,0,0,mBitmapPaint);
        if(drawMode){
            canvas.drawPath(mPath,mPaint);
        }else {
            canvas.drawPath(mPath,mEraserPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchDown(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if(drawMode){
                    touchUp(mPaint);
                }else {
                    touchUp(mEraserPaint);
                }
                invalidate();
                break;

        }
        return true;
    }

    private void touchDown(float x,float y){
        mPath.reset();
        mPath.moveTo(x,y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x,float y){
        float dx = Math.abs(x-mX);
        float dy = Math.abs(x-mY);
        if(dx>= TOUCH_TOLERANCE || dy>= TOUCH_TOLERANCE){
            mPath.quadTo(mX,mY,(x+mX)/2,(y+mY)/2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp(Paint paint){
        mPath.lineTo(mX,mY);
        mCanvas.drawPath(mPath,paint);
        mPath.reset();
    }
}

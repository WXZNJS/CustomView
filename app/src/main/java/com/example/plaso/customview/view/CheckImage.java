package com.example.plaso.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.plaso.customview.R;

/**
 * Created by zhhang on 2018/8/29.
 */

public class CheckImage extends LinearLayout{
    int checkImage;
    int image;
    ImageView imageView;
    boolean isChecked = false;

    public CheckImage(Context contetx){
        super(contetx);
    }

    public CheckImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initResource(context,attrs);
    }

    public CheckImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResource(context,attrs);
    }

    private void initResource(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.checkImageView);
        checkImage = typedArray.getResourceId(R.styleable.checkImageView_imageCheck, 0);
        image = typedArray.getResourceId(R.styleable.checkImageView_image, 0);
        typedArray.recycle();
        initView(context);
    }

    private void initView(Context mContetx){
        View view = LayoutInflater.from(mContetx).inflate(R.layout.check_image_layout, this);
        imageView = findViewById(R.id.imageview);
        imageView.setImageResource(image);
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
        imageView.setImageResource(isChecked ? checkImage:image);
    }

    public boolean getChecked(){
        return isChecked;
    }
}

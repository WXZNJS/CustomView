package com.example.plaso.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.plaso.customview.R;
import com.example.plaso.customview.camera.AppConstant;
import com.example.plaso.customview.camera.CameraUtil;

import java.io.File;

public class SelfCameraActivity extends Activity implements View.OnClickListener{
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_camera_layout);
        initView();
    }

    private void initView(){
        Button button = findViewById(R.id.take_photo);
        image = findViewById(R.id.image);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo:
                CameraUtil.getInstance().camera(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != AppConstant.RESULT_CODE.RESULT_OK){
            return;
        }

        if(requestCode == AppConstant.REQUEST_CODE.CAMERA){
            String img_path = data.getStringExtra(AppConstant.KEY.IMG_PATH);
            int picWidth = data.getIntExtra(AppConstant.KEY.PIC_WIDTH, 0);
            int picHeight = data.getIntExtra(AppConstant.KEY.PIC_HEIGHT, 0);
            Log.d("zh","width_"+picWidth);
            Log.d("zh","height_"+picHeight);
            Log.d("zh","imagePath_"+img_path);
            Glide.with(this).load(new File(img_path)).into(image);

        }
    }
}

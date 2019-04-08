package com.example.plaso.customview.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.plaso.customview.R;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

/**
 * Created by zhhang on 2019/4/1.
 */
public class CameraActivity extends Activity implements View.OnClickListener{

    CameraView cameraView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        cameraView = findViewById(R.id.camera);
        findViewById(R.id.take_photo).setOnClickListener(this);
        setListener();
    }


    private void setListener(){
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(PictureResult result) {
                super.onPictureTaken(result);
            }

            @Override
            public void onCameraError(CameraException exception) {
                super.onCameraError(exception);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo:
                cameraView.takePicture();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        cameraView.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraView.destroy();
    }


}

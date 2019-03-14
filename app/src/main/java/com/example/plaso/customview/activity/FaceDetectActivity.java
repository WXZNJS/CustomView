package com.example.plaso.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.plaso.customview.R;
import com.example.plaso.customview.okhttp.CommonRequest;
import com.example.plaso.customview.okhttp.HttpClient;
import com.example.plaso.customview.util.DpUtil;
import com.example.plaso.customview.util.SystemUtil;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FaceDetectActivity extends Activity implements View.OnClickListener,TakePhoto.TakeResultListener,InvokeListener {

    ImageView imageView;
    FrameLayout container;
    TakePhoto takePhoto;
    InvokeParam invokeParam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_detect_layout);
        initView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }


    private void initView(){
        Button photoButton = findViewById(R.id.take_photo);
        imageView = findViewById(R.id.image);
        container = findViewById(R.id.container);
        photoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo:
                takePhoto();
                break;
        }
    }

    private void takePhoto(){
        takePhoto.onPickFromGallery();
    }

    float ration;
    @Override
    public void takeSuccess(TResult result) {
        String imagePath = result.getImage().getOriginalPath();
        File file = new File(imagePath);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ration = (SystemUtil.getScreenWH(this)[0]-DpUtil.dp2px(this,100))/ (float)bitmap.getWidth();
        Glide.with(this).load(file).into(imageView);
        detectFace(file);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    private void detectFace(File file){
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        Map<String,Object> params = new HashMap<>();
        params.put("api_key","XVsqdQhLK7MPGaJCokHGo1dhSiC6yahC");
        params.put("api_secret","eXml6GbGHCkwxfa5nGzDje8JXnmov4xB");
        params.put("image_file",file);
        params.put("return_landmark","1");
        HttpClient.getInstance().httpClient.newCall(CommonRequest.createPostFileRequest(url,params)).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String resp = response.body().string();
                        drawLandMark(resp);
                    }
                }
        );
    }

    List<int[]> pointList = new ArrayList<>();
    private void drawLandMark(String resp){
        try {
            JSONObject obj = new JSONObject(resp);
            JSONObject landMarkObj =(JSONObject) (obj.getJSONArray("faces").get(0));
            JSONObject objList = landMarkObj.getJSONObject("landmark");
            Iterator<String> keys = objList.keys();
            while (keys.hasNext()){
                JSONObject jsonObject = objList.getJSONObject(keys.next());
                pointList.add(new int[]{jsonObject.getInt("x"),jsonObject.getInt("y")});
            }
            runOnUiThread(() -> drawPaint(pointList));
        }catch (Exception e){
            Log.d("zh","errorParse_"+e.getMessage());
        }

    }

    private void drawPaint(List<int[]> dataList){
        int leftMargin = DpUtil.dp2px(this, 50);
        //设置位置
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for(int i=0;i<dataList.size();i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.circle);
            int[] ints = dataList.get(i);
            layoutParams.setMargins((int)(ints[0]*ration)+leftMargin,(int)(ints[1]*ration),0,0);
            container.addView(imageView,layoutParams);
        }
    }



}

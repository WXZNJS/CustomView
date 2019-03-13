package com.example.plaso.customview.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.plaso.customview.R;
import com.example.plaso.customview.dataBean.AgingFaceBean;
import com.example.plaso.customview.dataBean.UploadFaceBean;
import com.example.plaso.customview.okhttp.ApiConfig;
import com.example.plaso.customview.okhttp.CommonRequest;
import com.example.plaso.customview.okhttp.HttpClient;
import com.google.gson.Gson;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TokePhotoActivity extends Activity implements View.OnClickListener,TakePhoto.TakeResultListener,InvokeListener {

    private static final String TAKE_PHOTO="take_photo";
    private static final String SELECT_PIC="select_pic";
    private static final int GET_AGING_DATA_SUCC = 1;
    InvokeParam invokeParam;
    TakePhoto takePhoto;
    ImageView imageView;
    TextView ageText;
    Button addBt;
    Button reduceBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_photo_activity);
        initView();
    }

    private void initView(){
        Button selectPic = findViewById(R.id.select_pic);
        Button takePhoto = findViewById(R.id.take_photo);
        reduceBt = findViewById(R.id.reduce);
        addBt = findViewById(R.id.add);
        imageView = findViewById(R.id.imageView);
        ageText = findViewById(R.id.age_text);

        selectPic.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        reduceBt.setOnClickListener(this);
        addBt.setOnClickListener(this);
        reduceBt.setClickable(false);
        addBt.setClickable(false);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_pic:
                takePhoto(SELECT_PIC);
                break;
            case R.id.take_photo:
                takePhoto(TAKE_PHOTO);
                break;
            case R.id.reduce:
                reduceAge();
                break;
            case R.id.add:
                addAge();
                break;
        }
    }



    private void takePhoto(String type){
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);

        if(type.equals(TAKE_PHOTO)){
            takePhoto.onPickFromGallery();
        }else if(type.equals(SELECT_PIC)){
            takePhoto.onPickFromCapture(imageUri);
        }

    }


    @Override
    public void takeSuccess(TResult result) {
        String originPath = result.getImage().getOriginalPath();
        File file = new File(originPath);
        Glide.with(this).load(file).into(imageView);
//        changeFace(file);
        dealAgingFaceData(ApiConfig.TEST_DATA);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    //调用人脸老化接口
    private void changeFace(File file){
        String url = "https://api.cmfapp.co.uk/api2/";
        Map<String,Object> params = new HashMap<>();
        params.put("action","upload_image");
        params.put("api_key",ApiConfig.AGING_KEY);
        params.put("image",file);
        HttpClient.getInstance().httpClient.newCall(CommonRequest.createPostFileRequest(url,params)).
                enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.d("zh","error_"+e.getMessage());

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String resp = response.body().string();
                                Log.d("zh","resp_"+resp);
                                dealFaceBean(resp);
                            }
                        }
                );
    }


    private void dealFaceBean(String faceData){
        UploadFaceBean faceBean = new Gson().fromJson(faceData, UploadFaceBean.class);
        if(faceBean.isSuccess()){
            getAgingFace(faceBean.getSession_id());
        }else {
            Toast.makeText(this,"fail upload face",Toast.LENGTH_SHORT).show();
        }
    }

    //获取老化效果图
    private void getAgingFace(String sessionId){
        String url = "https://api.cmfapp.co.uk/api2/";
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("action","apply_effects");
        paramsMap.put("api_key",ApiConfig.AGING_KEY);
        paramsMap.put("session_id",sessionId);
        paramsMap.put("effects[effect_id]","100");

        HttpClient.getInstance().httpClient.newCall(CommonRequest.createPostRequest(url,paramsMap)).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String faceResp =response.body().string();
                        Log.d("zh","faceResp_"+faceResp);
//                        new Handler().post(() -> dealAgingFaceData(faceResp));
                    }
                }
        );
    }

    List<AgingFaceBean.DataEntity.Effect_resultsEntity> dataList;
    private void dealAgingFaceData(String agingData){
        Log.d("zh","threadName_"+Thread.currentThread().getName());
        AgingFaceBean agingFaceBean = new Gson().fromJson(agingData, AgingFaceBean.class);
        if(agingFaceBean.getSuccess() == GET_AGING_DATA_SUCC){
            dataList = agingFaceBean.getData().getEffect_results();
            Log.d("zh","dataListSize_"+dataList.size());
            if(dataList.size()>0){
                addBt.setClickable(true);
                reduceBt.setClickable(true);
            }
        }else {
            Toast.makeText(this,"aging face fail",Toast.LENGTH_SHORT).show();
        }
    }

    int index = 0;
    private void addAge(){
        if(dataList!=null && dataList.size() > index+1){
            index += 1;
            String fileUrl = dataList.get(index).getOutput_file();
            Log.d("zh","fileUrl_"+fileUrl);
            setAgeText(String.valueOf(dataList.get(index).getAge()));
           Glide.with(this).load(Uri.parse(fileUrl)).into(imageView);
        }
    }

    private void reduceAge(){
        if(dataList != null && index>0){
            index -= 1;
            String fileUrl = dataList.get(index).getOutput_file();
            Log.d("zh","fileUrl_"+fileUrl);
            setAgeText(String.valueOf(dataList.get(index).getAge()));
            Glide.with(this).load(Uri.parse(fileUrl)).into(imageView);
        }
    }

    private void setAgeText(String text){
        ageText.setText(text+"岁");
    }
}

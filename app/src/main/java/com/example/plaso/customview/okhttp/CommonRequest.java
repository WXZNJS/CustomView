package com.example.plaso.customview.okhttp;

import android.support.annotation.Nullable;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CommonRequest {

    /**
     * 普通post请求
     * @param url
     * @param requestParams
     * @return
     */
    public static Request  createPostRequest(String url,@Nullable Map<String,String> requestParams){
        FormBody.Builder builder = new FormBody.Builder();
        if(requestParams!=null){
            for(Map.Entry<String,String> entry:requestParams.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }

        FormBody build = builder.build();
        return new Request.Builder()
                .url(url)
                .post(build)
                .build();

    }


    /**
     * get请求
     * @param url
     * @param requestParams
     * @return
     */
    public static Request createGetRequest(String url,@Nullable Map<String,String> requestParams){
        StringBuilder builder = new StringBuilder(url).append("?");
        if(requestParams!=null){
            for(Map.Entry<String,String> entry:requestParams.entrySet()){
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder()
                .url(builder.substring(0,builder.length()-1))
                .get()
                .build();

    }

    /**
     * 表单post文件
     */
    private static final MediaType IMAGE_TYPE = MediaType.parse("image/jpeg");
    private static final MediaType ZIP_TYPE = MediaType.parse("application/zip");
    public static Request createPostFileRequest(String url,Map<String,Object> requestParams){
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if(requestParams!=null){
            for(Map.Entry<String,Object> entry:requestParams.entrySet()){
                if(entry.getValue() instanceof File){
                   builder.addFormDataPart(entry.getKey(),entry.getKey(),RequestBody.create(IMAGE_TYPE,(File)entry.getValue()));
                }else {
                    builder.addFormDataPart(entry.getKey(),(String) entry.getValue());
                }
            }
        }
        return new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
    }


}



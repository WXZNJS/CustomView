package com.example.plaso.customview.okhttp;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionPool;

public class HttpClient {

    public okhttp3.OkHttpClient httpClient;

    private HttpClient() {
        initClient();
    }

    //静态内部类在有引用后才会加载到内存
    private static class ClientHolder {
        private static HttpClient client = new HttpClient();
    }

    public static HttpClient getInstance() {
        return ClientHolder.client;
    }


    private void initClient(){

        final X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        ConnectionPool pool = new ConnectionPool(5, 30, TimeUnit.SECONDS);
        httpClient = new okhttp3.OkHttpClient
                .Builder().readTimeout(600, TimeUnit.SECONDS).writeTimeout(600, TimeUnit.SECONDS).connectTimeout(600, TimeUnit.SECONDS)
                .connectionPool(pool)
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();

    }

}

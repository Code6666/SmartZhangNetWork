package com.base.network.basenetwork.baselib;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by d on 2018/9/25.
 * Retrofit 基类,对Retrofit进行各种(OKhttp gson rxjava等)相关配置
 * 自定义的可以继承此类
 * 暴露创建新的newRetrofit()方法
 */

public class BaseRetrofit {

    /**
     * 创建 Retrofit
     *
     * @return Retrofit
     */
    protected static Retrofit newRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url) // Base URL: 总是以 /结尾
                .client(BaseOkHttpClient.getOkHttpClient()) //设置okhttp
                .addConverterFactory(GsonConverterFactory.create()) //Gson解析数据
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Service接口现在可以作为Observable返回了
                .build();
    }


    /**
     *
     * @param url
     * @param okHttpClient 自定义 OkHttpClient
     * @return Retrofit
     */
    protected static Retrofit newRetrofit(String url, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(url) // Base URL: 总是以 /结尾
                .client(okHttpClient) //设置okhttp
                .addConverterFactory(GsonConverterFactory.create()) //解析数据
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Service接口现在可以作为Observable返回了
                .build();
    }


}

package com.base.network.basenetwork.baselib;


import android.text.TextUtils;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;

/**
 * Created by d on 2018/7/19.
 */

public class RxApiRetrofit extends BaseRetrofit {


    public <S> S createRxApi(Class<S> serviceClass) {
        return createRxApi(null, serviceClass, null);
    }

    public <S> S createRxApi(Class<S> serviceClass, OkHttpClient client) {
        return createRxApi(null, serviceClass, client);
    }


    public <S> S createRxApi(String base_url, Class<S> serviceClass, OkHttpClient client) {
        String baseURL = TextUtils.isEmpty(base_url) ? "" : base_url;   // 先赋值
        if (TextUtils.isEmpty(baseURL)) {   //赋值后 依然为空 反射查找
            try {
                Field field1 = serviceClass.getField("BASE_URL");
                baseURL = (String) field1.get(serviceClass);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.getMessage();
                e.printStackTrace();
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
        }

        // todo 遗留问题: 反射查找依然为空呢?
        if (client == null) {
            return createRxApi(baseURL, serviceClass);
        } else {
            return newRetrofit(baseURL, client).create(serviceClass);
        }

    }


    public <T> T createRxApi(String base_url, Class<T> serviceClass) {
        return newRetrofit(base_url).create(serviceClass);
    }
}

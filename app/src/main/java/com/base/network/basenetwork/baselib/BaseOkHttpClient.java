package com.base.network.basenetwork.baselib;

import android.support.annotation.NonNull;
import android.util.Log;


import com.base.network.basenetwork.BuildConfig;
import com.base.network.basenetwork.utils.AppUtils;
import com.base.network.basenetwork.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by d on 2018/9/25.
 */

public class BaseOkHttpClient {

    private static final String TAG = "mBaseOkHttpClient";

    /**
     * 设置 OkHttpClient 并对外返回
     *
     * @return OkHttpClient
     */
    @NonNull
    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(5, TimeUnit.SECONDS);    //连接超时
        builder.readTimeout(8, TimeUnit.SECONDS);      //读取超时
        builder.writeTimeout(10, TimeUnit.SECONDS);     //写入超时

        //cache url
        File httpCacheDirectory = new File(AppUtils.getContext().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        builder.cache(cache);

        // addNetworkInterceptor添加的是网络拦截器，在request和resposne是分别被调用一次
        // addinterceptor添加的是aplication应用拦截器，只会在response被调用一次
//
//        应用拦截器
//
//        不需要担心中间响应，如重定向和重试。
//        总是调用一次，即使从缓存中提供HTTP响应。
//        遵守应用程序的原始意图。不关心OkHttp注入的头像If-None-Match。
//        允许短路而不打电话Chain.proceed()。
//        允许重试并进行多次呼叫Chain.proceed()。

//        网络拦截器
//
//        能够对重定向和重试等中间响应进行操作。
//        没有为缓存的响应调用网络短路。
//        观察数据，就像通过网络传输一样。
//        访问Connection该请求。

        if (BuildConfig.DEBUG) {    // 第三方的日志拦截器
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);    //全部信息
            builder.addInterceptor(interceptor);
        }

        builder.addInterceptor(appIntercepter);         // 自定义的应用拦截器: 设置公共参数
//        builder.addInterceptor(cacheIntercepter);       // 自定义的应用拦截器: 设置缓存策略//这里大家一定要注意了是addNetworkOnterceptor别搞错了啊
//        builder.addNetworkInterceptor(netIntercepter);  // 自定义的网络拦截器: 重试或者重连次数等

//        builder.addInterceptor(new BridgeInterceptor(cookiejar));
//        builder.addInterceptor(new CallServerInterceptor(boolean forWebSocket));

//        builder.addInterceptor(new ConnectInterceptor(new OkHttpClient()));

        //builder.retryOnConnectionFailure(true); //连接失败后是否重新连接
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }


    //应用拦截器：主要用于设置公共参数，头信息，日志拦截等,有点类似Retrofit的Converter
    private static Interceptor appIntercepter = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = processRequest(chain.request());
            Response response = processResponse(chain.proceed(request));
            return response;
        }
    };

    //访问网络之前，处理Request(比如请求头添加Cookie或者token)
    private static Request processRequest(Request request) {
//        String session = CacheManager.restoreLoginInfo(BaseApplication.getContext()).getSession();
//        String session = "restoreLoginInfo.getSession";
        return request
                .newBuilder()
//                .addHeader("Cookie", "JSESSIONID=" + session)
//                .addHeader("lang", Constants.LANGUAGE)  // 语言-国际化
                .build();
    }

    //访问网络之后，处理Response(这里可以考虑token失效等统一处理)
    private static Response processResponse(Response response) {
        Log.d(TAG, "processResponse=" + response.toString());
        return response;
    }


    //应用拦截器：设置缓存策略
//    noCache ：不使用缓存，全部走网络
//    noStore ： 不使用缓存，也不存储缓存
//    onlyIfCached ： 只使用缓存
//    maxAge ：设置最大失效时间，失效则不使用
//    maxStale ：设置最大失效时间，失效则不使用
//    minFresh ：设置最小有效时间，失效则不使用
//    FORCE_NETWORK ： 强制走网络
//    FORCE_CACHE ：强制走缓存
    //注意:
    //max-stale在请求头设置有效，在响应头设置无效。
    //max-stale和max-age同时设置的时候，缓存失效的时间按最长的算。

    private static Interceptor cacheIntercepter = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            //无网的时候强制使用缓存
//            if (NetUtil.getNetState() == NetUtil.NetState.NET_NO) {
            if (!NetUtils.isNetworkAvailable(AppUtils.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)//缓存策略
                        .build();
            }

            Response response = chain.proceed(request);

            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//            if (NetUtil.getNetState() != NetUtil.NetState.NET_NO) {
            if (NetUtils.isNetworkAvailable(AppUtils.getContext())) {
                String cacheControl = request.cacheControl().toString();
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return response.newBuilder()
                        //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };


    //网络拦截器：主要用于重试或重写
    private static Interceptor netIntercepter = new Interceptor() {

        private int sMaxTryCount = 3;   //最多尝试次数

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int tryCount = 0;
            while (!response.isSuccessful() && tryCount < sMaxTryCount) {
                tryCount++;
                response = chain.proceed(request);
            }
            return response;
        }
    };

}

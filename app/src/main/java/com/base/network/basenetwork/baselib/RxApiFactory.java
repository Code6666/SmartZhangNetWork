package com.base.network.basenetwork.baselib;


/**
 * Created by d on 2018/7/19.
 * api 管理类
 */
public class RxApiFactory {

    protected static final Object monitor = new Object();

    static IRxApi rxApiSingleton = null;

    //return Singleton
    public static IRxApi getRxApiSingleton() {
        if (rxApiSingleton == null) {
            synchronized (monitor) {
                if (rxApiSingleton == null) {
                    rxApiSingleton = new RxApiRetrofit().createRxApi(IRxApi.class);
                }
            }
        }
        return rxApiSingleton;
    }


}

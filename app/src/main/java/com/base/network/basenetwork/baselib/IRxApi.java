package com.base.network.basenetwork.baselib;

import com.base.network.basenetwork.api.entry.response.AppUpdateResultBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by d on 2018/9/26.
 */

public interface IRxApi {

    // 服务器地址
    String BASE_URL = "https://www.sxrhmall.com/";

    @GET("/rhshop/app/upgrade/v1/check?/")
    Observable<AppUpdateResultBean> getAppUpdateInfo(@Query("clientType") String clientType,
                                                     @Query("verCode") int verCode,
                                                     @Query("lang") String lang);

}

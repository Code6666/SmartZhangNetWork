package com.base.network.basenetwork.test;

import android.util.Log;

import com.base.network.basenetwork.api.entry.response.AppUpdateResultBean;
import com.base.network.basenetwork.baselib.BasePresenter;
import com.base.network.basenetwork.baselib.IRxApi;
import com.base.network.basenetwork.baselib.RxApiFactory;
import com.base.network.basenetwork.rxscheduler.RxSchedulers;

import io.reactivex.functions.Consumer;

/**
 * Created by d on 2018/9/26.
 */

public class UpdatePresenter extends BasePresenter {

    private static final IRxApi sApi = RxApiFactory.getRxApiSingleton();


    //test
    public void getAppUpData() {
        sApi.getAppUpdateInfo("ANDROID", 2, "zh_CN")
                .compose(RxSchedulers.io2Main())
                .subscribe(new Consumer<AppUpdateResultBean>() {
                    @Override
                    public void accept(AppUpdateResultBean appUpdateResultBean) throws Exception {
                        Log.i("success", appUpdateResultBean.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }


}

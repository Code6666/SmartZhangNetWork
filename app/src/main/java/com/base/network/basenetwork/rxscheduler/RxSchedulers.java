package com.base.network.basenetwork.rxscheduler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by d on 2018/9/25.
 * 线程切换
 */

public class RxSchedulers {

    public static <T> BaseScheduler io2Main() {
        return new BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread());
    }

    public static <T> BaseScheduler computation2Main() {
        return new BaseScheduler<T>(Schedulers.computation(), AndroidSchedulers.mainThread());
    }

    public static <T> BaseScheduler newThread2Main() {
        return new BaseScheduler<T>(Schedulers.newThread(), AndroidSchedulers.mainThread());
    }

    public static <T> BaseScheduler single2Main() {
        return new BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread());
    }

    public static <T> BaseScheduler trampoline2Main() {
        return new BaseScheduler<T>(Schedulers.trampoline(), AndroidSchedulers.mainThread());
    }

}

package com.base.network.basenetwork.baselib;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by d on 2018/7/19.
 */

public abstract class BaseObserver<E> extends DisposableObserver<BaseResultEntity<E>> {
    private static final String TAG = "mBaseObserver";


    public BaseObserver() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(BaseResultEntity<E> value) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }


}

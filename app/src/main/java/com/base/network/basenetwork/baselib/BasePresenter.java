package com.base.network.basenetwork.baselib;


import com.base.network.basenetwork.rxscheduler.RxSchedulers;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;


public class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


}

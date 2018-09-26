package com.base.network.basenetwork;

import android.app.Application;
import android.content.Context;

/**
 * Created by d on 2018/9/26.
 */

public class BaseApplication extends Application {

    static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = this;
    }


    public static Context getContext() {
        return mAppContext;
    }
}

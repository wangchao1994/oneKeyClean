package com.go.onekeyclean.base;

import android.app.Application;

/**
 * Created by wangchao on 17-11-6.
 */

public class BaseApplication extends Application {

    public static BaseApplication baseApplication;

    public static BaseApplication getInstance(){
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        MyCrashHandler myCrashHandler = MyCrashHandler.getInstance();
        myCrashHandler.init(baseApplication);
        Thread.currentThread().setUncaughtExceptionHandler(myCrashHandler);
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myTid());
        super.onLowMemory();
    }
}

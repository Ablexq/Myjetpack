package com.example.mycardview.activitylifecyclecallbacks;

import android.app.Application;

//
// https://blog.csdn.net/xiaoyantan/article/details/70337980
public class MyApplication extends Application {
    public MyActivityLifecycleCallbacks activityLifecycleCallbacks = new MyActivityLifecycleCallbacks();

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
}

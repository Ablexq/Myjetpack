package com.example.mycardview.activitylifecyclecallbacks;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks, ActivityState {

    //所有启动的activity列表
    private List<Activity> activityList = new ArrayList<>();
    //所有前台activity列表
    private List<Activity> resumeActivity = new ArrayList<>();

    /*============= ActivityState =========================*/

    @Override
    public int count() {
        return activityList.size();
    }

    @Override
    public boolean isFront() {
        return resumeActivity.size() > 0;
    }

    @Override
    public Activity current() {
        return activityList.size() > 0 ? activityList.get(0) : null;
    }

    /*============= MyActivityLifecycleCallbacks =========================*/
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityList.add(0, activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (!resumeActivity.contains(activity)) {
            resumeActivity.add(activity);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        resumeActivity.remove(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityList.remove(activity);
    }

}
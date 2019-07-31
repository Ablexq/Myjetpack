package com.example.mycardview.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyLifecycleObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        System.out.println("LifecycleObserver======onCreate========");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        System.out.println("LifecycleObserver======onStart========");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        System.out.println("LifecycleObserver======onResume========");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        System.out.println("LifecycleObserver======onPause========");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        System.out.println("LifecycleObserver======onStop========");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        System.out.println("LifecycleObserver======onDestroy========");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny() {
        System.out.println("LifecycleObserver======onAny========");
    }

}

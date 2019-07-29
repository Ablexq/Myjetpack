package com.example.mycardview.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;

//1. 实现LifecycleOwner接口，并重写getLifecycle方法。
//2. 手动在每个生命周期方法中做标记。
public class MyActivity1 extends Activity implements LifecycleOwner {
    private LifecycleRegistry mLifecycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLifecycle = (LifecycleRegistry) new LifecycleRegistry(this);
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
//        //或
//        mLifecycle.markState(Lifecycle.State.STARTED);

        //监听
        getLifecycle().addObserver(new LifecycleObserver() {

            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            private void onCreate() {
                System.out.println("======onCreate========");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycle.markState(Lifecycle.State.STARTED); //start - pause
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycle.markState(Lifecycle.State.RESUMED);  //resume
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycle.markState(Lifecycle.State.DESTROYED); //destroy
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }
}

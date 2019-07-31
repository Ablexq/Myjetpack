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
        System.out.println("Activity======onCreate========");

        mLifecycle = (LifecycleRegistry) new LifecycleRegistry(this);
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
//        //或
//        mLifecycle.markState(Lifecycle.State.CREATED);

        //监听
        getLifecycle().addObserver(new MyLifecycleObserver());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
//        mLifecycle.markState(Lifecycle.State.STARTED);
        System.out.println("Activity======onStart========");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
//        mLifecycle.markState(Lifecycle.State.RESUMED);
        System.out.println("Activity======onResume========");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        System.out.println("Activity======onPause========");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        System.out.println("Activity======onStop========");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
//        mLifecycle.markState(Lifecycle.State.DESTROYED);
        System.out.println("Activity======onDestroy========");
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }
}

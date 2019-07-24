package com.example.mycardview.activitylifecyclecallbacks;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycardview.R;

public class MainActivity extends AppCompatActivity {

    private MyApplication application;
    private MyActivityLifecycleCallbacks activityLifecycleCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = ((MyApplication) this.getApplication());
        activityLifecycleCallbacks = application.activityLifecycleCallbacks;


    }

    @Override
    protected void onStart() {
        super.onStart();

        int count = activityLifecycleCallbacks.count();
        Activity activity = activityLifecycleCallbacks.current();
        boolean front = activityLifecycleCallbacks.isFront();
        System.out.println("onStart count =: " + count);
        System.out.println("onStart activity =: " + activity);
        System.out.println("onStart font =: " + front);
    }

    @Override
    protected void onStop() {
        super.onStop();

        int count = activityLifecycleCallbacks.count();
        Activity activity = activityLifecycleCallbacks.current();
        boolean front = activityLifecycleCallbacks.isFront();
        System.out.println("onStop count =: " + count);
        System.out.println("onStop activity =: " + activity);
        System.out.println("onStop font =: " + front);
    }
}

package com.example.mycardview.livedataviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MyActivity2 extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MyViewModel viewModel = ViewModelProviders.of(MyActivity2.this).get(MyViewModel.class);
        viewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("s===============" + s);//实时监听
            }
        });

        new Button(this).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //主线程
                viewModel.getName().setValue("666");
                //子线程
                viewModel.getName().postValue("999");
            }
        });
    }

}

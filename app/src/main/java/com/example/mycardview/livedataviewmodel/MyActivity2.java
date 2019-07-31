package com.example.mycardview.livedataviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mycardview.R;

public class MyActivity2 extends FragmentActivity implements View.OnClickListener {

    private MyViewModel viewModel;
    private TextView textView1;
    private ObservableUser observableUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        initViews();

        viewModel = ViewModelProviders.of(MyActivity2.this).get(MyViewModel.class);
        viewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("s===============" + s);//实时监听
                textView1.setText(s);
            }
        });

        observableUser = new ObservableUser();
        observableUser.name.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                String s = ((ObservableField<String>) sender).get();
                System.out.println("sender================" + s);
                System.out.println("propertyId============" + propertyId);
                textView1.setText(s);
            }
        });

    }

    private void initViews() {
        textView1 = ((TextView) this.findViewById(R.id.tv_vm1));
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //主线程
                viewModel.getName().setValue("11111111111111");
                break;
            case R.id.btn2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //子线程
                        viewModel.getName().postValue("2222222222222222");
                    }
                }).start();
                break;
            case R.id.btn3:
                observableUser.name.set("333333333333333");
                break;
            case R.id.btn4:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        observableUser.name.set("44444444");
                    }
                }).start();
                break;
        }
    }
}

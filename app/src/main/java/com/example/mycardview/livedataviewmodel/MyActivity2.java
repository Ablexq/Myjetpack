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

import java.util.Date;

public class MyActivity2 extends FragmentActivity implements View.OnClickListener {

    private MyViewModel viewModel;
    private TextView textView1;
    private ObservableUser observableUser;
    private BaseObservableUser baseObservableUser;

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
                long time = new Date().getTime();
                textView1.setText(time + " : " + s);
            }
        });

        observableUser = new ObservableUser();
        observableUser.name.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                String s = ((ObservableField<String>) sender).get();
                System.out.println("sender================" + s);
                System.out.println("propertyId============" + propertyId);
                long time = new Date().getTime();
                textView1.setText(time + " : " + s);
            }
        });

        baseObservableUser = new BaseObservableUser();
        baseObservableUser.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                String name = ((BaseObservableUser) sender).getName();
                long time = new Date().getTime();
                textView1.setText(time + " : " + name);
            }
        });
    }

    private void initViews() {
        textView1 = ((TextView) this.findViewById(R.id.tv_vm1));
        findViewById(R.id.btn10).setOnClickListener(this);
        findViewById(R.id.btn11).setOnClickListener(this);
        findViewById(R.id.btn12).setOnClickListener(this);
        findViewById(R.id.btn20).setOnClickListener(this);
        findViewById(R.id.btn21).setOnClickListener(this);
        findViewById(R.id.btn22).setOnClickListener(this);
        findViewById(R.id.btn30).setOnClickListener(this);
        findViewById(R.id.btn31).setOnClickListener(this);
        findViewById(R.id.btn32).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn10:
                //主线程
                viewModel.getName().setValue("11111111111111");
                break;
            case R.id.btn11:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //子线程
                        viewModel.getName().postValue("2222222222222222");
                    }
                }).start();
                break;
            case R.id.btn12:
                //主线程
                viewModel.getName().setValue("2222222222222222");
                break;

            /*======================================================================*/
            case R.id.btn20:
                observableUser.name.set("333333333333333");
                break;
            case R.id.btn21:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        observableUser.name.set("44444444");
                    }
                }).start();
                break;
            case R.id.btn22:
                observableUser.name.set("44444444");
                break;

            /*======================================================================*/
            case R.id.btn30:
                baseObservableUser.setName("5555555555555555");
                break;
            case R.id.btn31:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        baseObservableUser.setName("6666666666666666");
                    }
                }).start();
                break;
            case R.id.btn32:
                baseObservableUser.setName("6666666666666666");
                break;
        }
    }
}

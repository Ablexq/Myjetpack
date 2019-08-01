package com.example.mycardview.livedataviewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mycardview.BR;

public class BaseObservableUser extends BaseObservable {

    private String name;

    @Bindable   //get方法上添加@Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name); //编译之后生成BR文件
    }
}

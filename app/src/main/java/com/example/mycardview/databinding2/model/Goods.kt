package com.example.mycardview.databinding2.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.mycardview.BR

class Goods(name: String, details: String, price: Float) : BaseObservable() {

    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @get:Bindable
    var name: String = name
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)  //只更新一个字段
        }

    var details: String = details
        //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyChange()                  //全部更新
        }

    var price: Float = price


}

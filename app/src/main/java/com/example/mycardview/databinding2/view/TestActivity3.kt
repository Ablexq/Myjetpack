package com.example.mycardview.databinding2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.*
import com.example.mycardview.R
import com.example.mycardview.databinding.ActivityTest3Binding
import com.example.mycardview.databinding2.model.ObservableGoods

/**
 * 双向数据绑定
 * 双向绑定的意思即为当数据改变时同时使视图刷新，而视图改变时也可以同时改变数据。
 * 看以下例子，当EditText的输入内容改变时，会同时同步到变量goods，
 * 绑定变量的方式比单向绑定多了一个等号：android:text="@={goods.name}"
 */
class TestActivity3 : AppCompatActivity() {

    private var map: ObservableMap<String, String>? = null
    private var list: ObservableList<String>? = null
    private lateinit var binding: ActivityTest3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test3)

        var observableGoods = ObservableGoods("name", 0.0f, "details")
        binding.goods = observableGoods
    }
}
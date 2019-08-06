package com.example.mycardview.databinding2.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.*
import com.example.mycardview.R
import com.example.mycardview.databinding.ActivityTest1Binding
import java.util.*

/**
 *     ObservableCollection
 *     dataBinding也提供了包装类用于替代原生的List和Map，
 *     分别是ObservableList和ObservableMap，当其包含的数据发生变化时，绑定的视图也会随之进行刷新。
 */
class TestActivity1 : AppCompatActivity() {

    private var map: ObservableMap<String, String>? = null
    private var list: ObservableList<String>? = null
    private lateinit var binding: ActivityTest1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test1)
        map = ObservableArrayMap<String, String>()
        map?.let {
            it["name"] = "xq"
            it["age"] = "18"
        }
        binding.map = map
        binding.key = "name"

        list = ObservableArrayList<String>()
        list?.let {
            it.add("1")
            it.add("2")
        }
        binding.list = list
        binding.index = 0
    }

    fun onClick(view: View) {
        map?.set("name", "xq,no==" + Random().nextInt(100))
        list?.set(0, "num===" + Random().nextInt(100))
    }
}
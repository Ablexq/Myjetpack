package com.example.mycardview.databinding3.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycardview.R
import com.example.mycardview.databinding.ActivityMy2Binding
import com.example.mycardview.databinding3.model.User
import androidx.databinding.ObservableArrayList
import com.example.mycardview.databinding3.model.Student
import android.widget.Toast
import androidx.databinding.ViewDataBinding

class My2Activity : AppCompatActivity() {

    val showDatas = ObservableArrayList<Student>()
    private lateinit var user: User
    private lateinit var binding: ActivityMy2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my2)
        binding.activity = this

        //初始化数据源
        for (i in 0..19) {
            showDatas.add(Student("学生:$i"))
        }
    }

    inner class Presenter {
        fun onBindItem(binding: ViewDataBinding, data: Any, position: Int) {
            binding.root.setOnClickListener {
                Toast.makeText(this@My2Activity, data.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}
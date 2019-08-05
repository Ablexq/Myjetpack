package com.example.mycardview.databinding.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycardview.R
import com.example.mycardview.databinding.ActivityDemo3Binding
import com.example.mycardview.databinding.model.ImageBean

class DemoActivity3 : AppCompatActivity() {

    lateinit var demo3Binding: ActivityDemo3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        demo3Binding = DataBindingUtil.setContentView(this, R.layout.activity_demo3)
        demo3Binding.imageBean=ImageBean("https://csdnimg.cn/pubfooter/images/edu-QR.png")
    }

}
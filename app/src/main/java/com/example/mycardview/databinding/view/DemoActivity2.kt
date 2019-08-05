package com.example.mycardview.databinding.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycardview.R
import com.example.mycardview.databinding.model.Animal
import com.example.mycardview.databinding.ActivityDemo2Binding
import com.example.mycardview.databinding.model.BgColor1
import com.example.mycardview.databinding.model.BgColor2
import com.example.mycardview.databinding.viewmodel.AnimalViewModel


class DemoActivity2 : AppCompatActivity() {

    private lateinit var bgColor1: BgColor1
    private lateinit var bgColor2: BgColor2
    private lateinit var viewModel: AnimalViewModel
    private lateinit var animal: Animal
    lateinit var demo2Binding: ActivityDemo2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        demo2Binding = DataBindingUtil.setContentView(this, R.layout.activity_demo2)
        animal = Animal("pig", 200, false)
        viewModel = AnimalViewModel(animal)
        demo2Binding.vm = viewModel

        demo2Binding.presenter = Presenter()

        bgColor1 = BgColor1()
        demo2Binding.bgColor1 = bgColor1

        bgColor2 = BgColor2()
        demo2Binding.bgColor2 = bgColor2
    }

    inner class Presenter {
        fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int): Unit {//必须带参数
            viewModel.shoutUp()
        }

        fun onClick(v: View) { //必须带参数
            viewModel.shoutDown()
        }

        fun onClickListenerBinding(isColor: Boolean) { //自定义监听方法
            bgColor1.isShowColor = !bgColor1.isShowColor
            bgColor2.isShowColor = !bgColor2.isShowColor
            Toast.makeText(this@DemoActivity2,
                    "bgColor1.isShowColor : ${bgColor1.isShowColor}\n" +
                            "bgColor2.isShowColor : ${bgColor2.isShowColor}",
                    Toast.LENGTH_SHORT).show()
        }
    }
}
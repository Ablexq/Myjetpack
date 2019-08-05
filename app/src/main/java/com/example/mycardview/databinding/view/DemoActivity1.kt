package com.example.mycardview.databinding.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycardview.R
import com.example.mycardview.databinding.ActivityDemo1Binding
import com.example.mycardview.databinding.model.Animal
import com.example.mycardview.databinding.viewmodel.AnimalViewModel

class DemoActivity1 : AppCompatActivity() {

    private lateinit var animal1: Animal
    private lateinit var animal2: Animal
    lateinit var animal0: Animal
    lateinit var demo1Binding: ActivityDemo1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        demo1Binding = DataBindingUtil.setContentView(this, R.layout.activity_demo1)
        demo1Binding.tv1.text = "HELLO WORLD！！！"

        //------------------------------------------------------

        animal0 = Animal("dog", 0, false)
        demo1Binding.vm = AnimalViewModel(animal0)

        //------------------------------------------------------
        animal1 = Animal("cat1", 1000, false)
//        demo2Binding.setVariable(BR.animal1, animal1)
        demo1Binding.animal1 = animal1

        animal2 = Animal("cat2", 2000, false)
        demo1Binding.animal2 = animal2
    }
}


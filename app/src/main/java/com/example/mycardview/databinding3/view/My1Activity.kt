package com.example.mycardview.databinding3.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycardview.R
import com.example.mycardview.databinding.ActivityMy1Binding
import com.example.mycardview.databinding3.model.User
import android.text.Editable


class My1Activity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var binding: ActivityMy1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my1)
        user = User("cat", "18")
        binding.user = user
        binding.presenter = My1Presenter()
    }

    inner class My1Presenter {

        fun onClick(user: User): Unit {
            Toast.makeText(this@My1Activity, "点击了：$user", Toast.LENGTH_SHORT).show()
        }

        fun afterTextChanged(s: Editable) {
            user.name = s.toString()
            binding.user = user   //再绑定一次
        }

        fun afterUserAge(s: Editable) {
            user.age = s.toString()
            binding.user = user     //再绑定一次
        }
    }
}
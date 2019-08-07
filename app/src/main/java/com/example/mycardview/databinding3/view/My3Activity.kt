package com.example.mycardview.databinding3.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mycardview.R
import com.example.mycardview.databinding.ActivityMy3Binding
import com.example.mycardview.databinding3.model.User
import android.text.Editable


class My3Activity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var binding: ActivityMy3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my3)
    }
}
package com.example.mycardview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mycardview.activitylifecyclecallbacks.MainActivity
import com.example.mycardview.cardview.CardViewActivity
import com.example.mycardview.lifecycle.MyActivity1
import com.example.mycardview.livedataviewmodel.MyActivity2
import kotlinx.android.synthetic.main.activity_index.*

//startActivity(Intent(MainActivity@this, SecondActivity::class.java))
//或者
//startActivity(Intent(this@MainActivity, SecondActivity::class.java))
//或者
//startActivity(Intent(this, SecondActivity::class.java))
class IndexActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.btn01 -> startActivity(Intent(this@IndexActivity, MyActivity1::class.java))
                R.id.btn02 -> startActivity(Intent(this@IndexActivity, MyActivity2::class.java))
                R.id.btn03 -> startActivity(Intent(IndexActivity@ this, CardViewActivity::class.java))
                R.id.btn04 -> startActivity(Intent(this, MainActivity::class.java))
                else -> ""
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)


        btn01.setOnClickListener(this)
        btn02.setOnClickListener(this)
        btn03.setOnClickListener(this)
        btn04.setOnClickListener(this)
    }
}
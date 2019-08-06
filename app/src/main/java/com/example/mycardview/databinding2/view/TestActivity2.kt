package com.example.mycardview.databinding2.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.*
import androidx.databinding.Observable
import com.example.mycardview.BR
import com.example.mycardview.R
import com.example.mycardview.databinding.ActivityTest2Binding
import com.example.mycardview.databinding2.model.Goods
import java.util.*


class TestActivity2 : AppCompatActivity() {

    private lateinit var goods: Goods
    private lateinit var binding: ActivityTest2Binding

    companion object {
        val TAG = TestActivity2::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test2)

        goods = Goods("code", "hi", 0.0f)
        binding.goods = goods
        binding.goodsHandler = GoodsHandler()

        goods.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (propertyId) {
                    BR.name -> Log.e(TAG, "-------------name--------------")    //notifyPropertyChanged
                    BR.details -> Log.e(TAG, "-------------details--------------")
                    BR._all -> Log.e(TAG, "-------------_all--------------")    //notifyChange
                    else -> Log.e(TAG, "---------------else-----------------")
                }
            }
        })
    }

    inner class GoodsHandler {
        fun changeGoodsName() {
            goods.name = ("code" + Random().nextInt(100))   //notifyPropertyChanged
            goods.price = (Random().nextInt(100)).toFloat()
        }

        fun changeGoodsDetails() {
            goods.details = ("hi" + Random().nextInt(100))  //notifyChange
            goods.price = (Random().nextInt(100)).toFloat()
        }
    }
}
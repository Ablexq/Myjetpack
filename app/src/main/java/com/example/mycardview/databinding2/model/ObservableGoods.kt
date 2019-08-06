package com.example.mycardview.databinding2.model

import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat


class ObservableGoods(name: String, price: Float, details: String) {
    var name: ObservableField<String> = ObservableField(name)
    var price: ObservableFloat = ObservableFloat(price)
    var details: ObservableField<String> = ObservableField(details)
}
package com.example.mycardview.databinding.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

import com.example.mycardview.BR


class BgColor1 : BaseObservable() {

    @get:Bindable
    var isShowColor: Boolean = false
        set(showColor) {
            field = showColor
            notifyPropertyChanged(BR.showColor)
        }
}

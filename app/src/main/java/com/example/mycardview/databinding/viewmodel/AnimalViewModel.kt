package com.example.mycardview.databinding.viewmodel

import androidx.databinding.ObservableField
import com.example.mycardview.databinding.model.Animal

class AnimalViewModel(var animal: Animal) {

    val info = ObservableField<String>("${animal.name} 叫了 ${animal.shoutCount}声..")

    fun shoutUp() {
        animal.shoutCount++
        animal.hasEat = !animal.hasEat
        info.set("${animal.name} 叫了 ${animal.shoutCount}声..")
    }

    fun shoutDown() {
        animal.shoutCount--
        animal.hasEat = !animal.hasEat
        info.set("${animal.name} 叫了 ${animal.shoutCount}声..")
    }

}
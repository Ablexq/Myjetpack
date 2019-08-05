package com.example.mycardview.databinding.view.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.databinding.BindingConversion

/*
* @BindingConversion
    作用于方法
    被该注解标记的方法，被视为dataBinding的转换方法。
    方法必须为公共静态（public static）方法，且有且只能有1个参数
* */
object BindingConversion {

    @JvmStatic
    @BindingConversion
    fun bindBooleanToVisibility(isVisible: Boolean): Int = if (isVisible) View.VISIBLE else View.GONE

    @JvmStatic
    @BindingConversion
    fun convertColorToDrawable(colorString: String): ColorDrawable {
        return ColorDrawable(Color.parseColor(colorString))
    }

    @JvmStatic
    @BindingConversion
    fun convertColorToDrawable(@ColorInt colorInt: Int): ColorDrawable {
        return ColorDrawable(colorInt)
    }

    @JvmStatic
    @BindingConversion
    fun convertColorToColorStateList(color: Int): ColorStateList {
        return ColorStateList.valueOf(color)
    }

    @JvmStatic
    @BindingConversion
    fun intToString(value: Int): String {
        return value.toString()
    }

    @JvmStatic
    @BindingConversion
    fun doubleToString(value: Double): String {
        return value.toString()
    }

    @JvmStatic
    @BindingConversion
    fun listToString(value: List<String>?) = value?.joinToString()

    @JvmStatic
    @BindingConversion
    fun conversionString(text: String): String {
        return "$text-BindingConversion"
    }
}
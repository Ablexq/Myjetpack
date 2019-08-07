package com.example.mycardview.databinding.util;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.databinding.BindingConversion;

/*
* @BindingConversion
    作用于方法
    被该注解标记的方法，被视为dataBinding的转换方法。
    方法必须为公共静态（public static）方法，且有且只能有1个参数
* */
public class JavaConverter {

    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }

    @BindingConversion
    public static ColorStateList convertColorToColorStateList(int color) {
        return ColorStateList.valueOf(color);
    }

    /*
     * 将字符串颜色值转化为ColorDrawable
     * */
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(String colorString) {
        return new ColorDrawable(Color.parseColor(colorString));
    }
}

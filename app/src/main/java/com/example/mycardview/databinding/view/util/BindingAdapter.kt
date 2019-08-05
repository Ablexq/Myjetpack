package com.example.mycardview.databinding.view.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mycardview.R
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import android.widget.Button
import android.widget.TextView




/*
* dataBinding 提供了 BindingAdapter 这个注解用于支持自定义属性，或者是修改原有属性。
* 注解值可以是已有的 xml 属性，例如 android:src、android:text等，也可以自定义属性然后在 xml 中使用
* BindingAdapter 更为强大的一点是可以覆盖 Android 原先的控件属性。必须全局修改了text的后缀
* https://blog.csdn.net/xiaowu_zhu/article/details/91952110
* */
object BindingAdapter {

    //BindingAdapter 和 BindingConversion 同时生效，而 BindingConversion 的优先级要高些
    @JvmStatic
    @BindingAdapter("android:text")
    fun setText(view: TextView, text: String) {
        view.text = "$text-BindingAdapter"
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageViewUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("app:resource")
    fun setImageViewResource(imageView: ImageView, resource: Int?) {
        imageView.setImageResource(resource ?: 0)
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl", "app:placeholder", "app:error", requireAll = false)
    fun loadImg(imageView: ImageView, url: String?, placeholder: Drawable, error: Drawable) {
        val requestOptions = RequestOptions().apply {
            this.error(error)
                    .placeholder(placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
        }
        Glide.with(imageView)
                .load(url)
                .apply(requestOptions)
                .into(imageView)
    }

//    @JvmStatic
//    @BindingAdapter("app:onChange")
//    public fun onTextChange(view: EditText, watcher: TextWatcher) {
//        view.addTextChangedListener(watcher)
//    }
//

    @BindingAdapter("android:paddingLeft")
    fun setPaddingLeft(view: View, padding: Int) {
        view.setPadding(padding,
                view.paddingTop,
                view.paddingRight,
                view.paddingBottom)
    }

    @BindingAdapter("visible")
    fun View.setIsVisible(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }

//
//    @JvmStatic
//    @BindingAdapter("icon")
//    fun setImageViewResource(imageView: ImageView, resourceId: Int?) {
//        if (resourceId != null)
//            imageView.setImageResource(resourceId)
//        else
//            imageView.visibility = View.GONE
//    }
}
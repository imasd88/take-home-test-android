package com.redbubble.redbubblehomework.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.redbubble.redbubblehomework.model.HomeModel

@BindingAdapter("desc")
fun setUpDesc(textView: TextView, homeModel: HomeModel) {
    textView.text = if (homeModel.type == "PRODUCT") {
        "${homeModel.currency} ${homeModel.amount}"
    } else {
        "by ${homeModel.artist}"
    }
}

@BindingAdapter("img")
fun getImage(imageView: ImageView, src: String) {
    with(src) {
        Glide.with(imageView).load(this).into(imageView)
    }
}
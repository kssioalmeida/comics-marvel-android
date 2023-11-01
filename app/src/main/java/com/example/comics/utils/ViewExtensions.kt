package com.example.comics.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible(visible: Boolean){
    if(visible) this.visible() else this.gone()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .into(this)
}
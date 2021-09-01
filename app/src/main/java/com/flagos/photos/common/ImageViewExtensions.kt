package com.flagos.photos.common

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.flagos.photos.R

fun ImageView.loadImageFromUrl(url: String) {
    val placeholder = ContextCompat.getDrawable(context, R.drawable.placeholder)
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(placeholder)
        .into(this)
}

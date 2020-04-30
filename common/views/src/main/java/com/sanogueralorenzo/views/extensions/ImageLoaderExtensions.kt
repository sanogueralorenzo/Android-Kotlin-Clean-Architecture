package com.sanogueralorenzo.views.extensions

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String, placeholder: CircularProgressDrawable) =
    Glide.with(this).asGif().load(url).placeholder(placeholder).into(this)

fun ImageView.loadImageRound(url: String, placeholder: CircularProgressDrawable) =
    Glide.with(this).load(url)
        .placeholder(placeholder)
        .apply(RequestOptions.circleCropTransform())
        .into(this)

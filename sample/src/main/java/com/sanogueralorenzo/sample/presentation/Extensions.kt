package com.sanogueralorenzo.sample.presentation

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadAvatar(email: String) =
    Glide.with(this)
        .load("https://api.adorable.io/avatars/285/$email")
        .apply(RequestOptions.circleCropTransform())
        .into(this)

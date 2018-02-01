package com.sanogueralorenzo.presentation

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sanogueralorenzo.presentation.injection.component.Injector

typealias f<T> = (T) -> Unit

fun Context.getAppInjector(): Injector = ((this as AppCompatActivity).application as App).injector

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.loadAvatar(email: String) = Glide.with(this).load("https://api.adorable.io/avatars/285/$email").apply(RequestOptions.circleCropTransform()).into(this)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}



@file:Suppress("MagicNumber")

package com.sanogueralorenzo.views.extensions

import android.util.TypedValue
import android.view.View

fun View.startEndPadding(dp: Int = 16) {
    toDp(dp).let {
        setPadding(it, 0, it, 0)
    }
}

fun View.setContainerPadding() {
    val vertical = toDp(8)
    val horizontal = toDp(16)
    setPadding(horizontal, vertical, horizontal, vertical)
}

fun View.toDp(dimension: Int): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dimension.toFloat(),
        resources.displayMetrics
    ).toInt()

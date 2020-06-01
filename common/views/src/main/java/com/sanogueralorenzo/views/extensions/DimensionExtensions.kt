@file:Suppress("MagicNumber")

package com.sanogueralorenzo.views.extensions

import android.util.TypedValue
import android.view.View

fun View.startEndPadding(dp: Int = 16) =
    toDp(dp).let { setPadding(it, paddingTop, it, paddingBottom) }

fun View.topBottomPadding(dp: Int = 16) =
    toDp(dp).let { setPadding(paddingLeft, it, paddingRight, it) }

fun View.topPadding(dp: Int = 16) = setPadding(paddingLeft, toDp(dp), paddingRight, paddingBottom)

fun View.bottomPadding(dp: Int = 16) = setPadding(paddingLeft, paddingTop, paddingRight, toDp(dp))

fun View.setPaddingAll(dp: Int = 16) = toDp(dp).let { setPadding(it, it, it, it) }

fun View.removePadding() {
    setPadding(0, 0, 0, 0)
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

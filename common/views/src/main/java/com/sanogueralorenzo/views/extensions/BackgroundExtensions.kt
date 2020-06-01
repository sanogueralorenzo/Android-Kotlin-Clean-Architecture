package com.sanogueralorenzo.views.extensions

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Set a rounded corners background for the view with the desired color and border
 *
 * Avoids having to create multiple XMLs
 */
fun View.setCustomBackground(backgroundOptions: BackgroundOptions) =
    with(backgroundOptions) {
        val drawable = GradientDrawable()
        if (color != null) drawable.setColor(ContextCompat.getColor(context, color))
        if (stroke != null) {
            drawable.setStroke(
                toDp(stroke.size), ContextCompat.getColorStateList(context, stroke.color)
            )
        }
        if (corners != null) {
            val radiusTopLeft = toDp(corners.topLeft).toFloat()
            val radiusTopRight = toDp(corners.topRight).toFloat()
            val radiusBottomRight = toDp(corners.bottomRight).toFloat()
            val radiusBottomLeft = toDp(corners.bottomLeft).toFloat()
            drawable.cornerRadii = floatArrayOf(
                radiusTopLeft,
                radiusTopLeft,
                radiusTopRight,
                radiusTopRight,
                radiusBottomRight,
                radiusBottomRight,
                radiusBottomLeft,
                radiusBottomLeft
            )
        }

        background = drawable
    }

/**
 * Set a rounded corners background for the view with the desired color and border
 * @param color the color resource
 * @param stroke Stroke that contains size and color
 * @param corners the radius value for all the corners in DPs (default: 0dp)
 */
data class BackgroundOptions(
    @ColorRes val color: Int? = null,
    val stroke: Stroke? = null,
    val corners: CornerRadius? = null
)

data class Stroke(
    val size: Int,
    val color: Int
)

data class CornerRadius(
    val topLeft: Int = 0,
    val topRight: Int = 0,
    val bottomRight: Int = 0,
    val bottomLeft: Int = 0
) {
    constructor(radius: Int) : this(
        radius,
        radius,
        radius,
        radius
    )
}

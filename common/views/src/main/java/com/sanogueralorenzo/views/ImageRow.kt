package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.sanogueralorenzo.views.extensions.loadImage
import com.sanogueralorenzo.views.extensions.style
import com.sanogueralorenzo.views.extensions.toDp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class ImageRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val placeholder = CircularProgressDrawable(context).also { it.style() }

    @ModelProp(group = "image")
    fun setDrawable(@DrawableRes drawable: Int) {
        setImageResource(drawable)
    }

    @ModelProp(group = "image")
    fun setUrl(url: String) {
        loadImage(url, placeholder)
    }

    @ModelProp
    fun setHeight(height: Int?) {
        height?.let { layoutParams.height = toDp(it) }
    }

    @JvmOverloads
    @ModelProp
    fun setImageScaleType(scale: ScaleType = ScaleType.FIT_CENTER) {
        scaleType = scale
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }
}

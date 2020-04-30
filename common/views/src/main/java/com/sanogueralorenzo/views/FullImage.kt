package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.sanogueralorenzo.views.extensions.loadImage
import com.sanogueralorenzo.views.extensions.style

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class FullImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val placeholder = CircularProgressDrawable(context).also { it.style() }

    @ModelProp
    fun setUrl(url: String) {
        loadImage(url, placeholder)
    }
}

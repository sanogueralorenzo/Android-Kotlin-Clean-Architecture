package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import kotlinx.android.synthetic.main.view_image_text.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ImageTextRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_image_text, this)
        title.setStyle(TextRow.TextStyle.HEADLINE)
        subtitle.setStyle(TextRow.TextStyle.BODY)
    }

    @TextProp
    fun setTitle(text: CharSequence) {
        title.text = text
    }

    @TextProp
    fun setSubtitle(text: CharSequence?) {
        subtitle.text = text
    }

    @ModelProp
    fun setTitleColor(@ColorRes color: Int?) {
        title.setBodyColor(color)
    }

    @ModelProp
    fun setImage(@DrawableRes drawable: Int) {
        image.setDrawable(drawable)
    }
}

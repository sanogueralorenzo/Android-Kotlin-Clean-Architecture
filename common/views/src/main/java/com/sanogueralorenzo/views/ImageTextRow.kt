package com.sanogueralorenzo.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.views.databinding.ViewImageTextBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ImageTextRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewImageTextBinding by viewBinding()

    init {
        View.inflate(context, R.layout.view_image_text, this)
        binding.title.setStyle(TextRow.TextStyle.SUBTITLE)
        binding.subtitle.setStyle(TextRow.TextStyle.BODY)
    }

    @TextProp
    fun setTitle(text: CharSequence) {
        binding.title.text = text
    }

    @TextProp
    fun setSubtitle(text: CharSequence?) {
        binding.subtitle.text = text
    }

    @ModelProp
    fun setImage(@DrawableRes drawable: Int) {
        binding.image.setDrawable(drawable)
    }

    @ModelProp
    fun setImageColor(@ColorRes color: Int?) {
        color?.let {
            ImageViewCompat.setImageTintList(
                binding.image,
                ColorStateList.valueOf(ContextCompat.getColor(context, it))
            )
        }
    }
}

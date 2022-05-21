package com.sanogueralorenzo.profile

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.sanogueralorenzo.profile.databinding.ViewProfileHeaderBinding
import com.sanogueralorenzo.views.TextRow
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.resources.R as G

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ProfileHeaderRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewProfileHeaderBinding by viewBinding()

    init {
        View.inflate(context, R.layout.view_profile_header, this)
        binding.name.setStyle(TextRow.TextStyle.SUBTITLE)
        binding.goals.setStyle(TextRow.TextStyle.BODY)
    }

    @TextProp
    fun setName(text: CharSequence) {
        binding.name.text = text
    }

    @TextProp
    fun setGoals(text: CharSequence?) {
        binding.goals.text = text
    }

    @ModelProp
    @JvmOverloads
    fun setImage(@DrawableRes drawable: Int = G.drawable.ic_logo) {
        binding.image.setDrawable(drawable)
    }
}

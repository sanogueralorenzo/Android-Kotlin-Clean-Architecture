package com.sanogueralorenzo.profile

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.sanogueralorenzo.views.TextRow
import kotlinx.android.synthetic.main.view_profile_header.view.*
import com.sanogueralorenzo.resources.R as G

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ProfileHeaderRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_profile_header, this)
        name.setStyle(TextRow.TextStyle.SUBTITLE)
        goals.setStyle(TextRow.TextStyle.BODY)
    }

    @TextProp
    fun setName(text: CharSequence) {
        name.text = text
    }

    @TextProp
    fun setGoals(text: CharSequence?) {
        goals.text = text
    }

    @ModelProp
    @JvmOverloads
    fun setImage(@DrawableRes drawable: Int = G.drawable.ic_logo) {
        image.setDrawable(drawable)
    }
}

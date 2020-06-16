package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.sanogueralorenzo.views.extensions.toDp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class DividerRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_divider, this)
    }

    @ModelProp
    fun setPaddingStart(paddingStart: Int?) {
        paddingStart?.let { this.setPadding(toDp(it), paddingTop, paddingRight, paddingBottom) }
    }
}

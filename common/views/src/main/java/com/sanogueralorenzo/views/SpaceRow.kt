package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.sanogueralorenzo.views.extensions.toDp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class SpaceRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    @ModelProp
    fun size(size: Int) {
        minimumHeight = toDp(size)
    }
}

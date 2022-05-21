package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.views.databinding.ViewLoadingBinding
import com.sanogueralorenzo.views.extensions.toDp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LoadingRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewLoadingBinding by viewBinding()

    init {
        View.inflate(context, R.layout.view_loading, this)
    }

    @ModelProp
    @JvmOverloads
    fun size(size: Int = 48) {
        binding.loading.minimumHeight = toDp(size)
    }
}

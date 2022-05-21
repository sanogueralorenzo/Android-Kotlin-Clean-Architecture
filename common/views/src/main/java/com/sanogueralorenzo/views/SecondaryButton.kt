package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.views.databinding.ViewSecondaryButtonBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class SecondaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewSecondaryButtonBinding by viewBinding()

    init {
        View.inflate(context, R.layout.view_secondary_button, this)
    }

    @TextProp
    fun setText(text: CharSequence) {
        binding.secondaryButton.text = text
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }

    companion object {
        fun create(c: Context, text: String, clickListener: () -> Unit): SecondaryButton =
            SecondaryButton(c).apply {
                binding.secondaryButton.text = text
                binding.secondaryButton.setOnClickListener { clickListener() }
            }
    }
}

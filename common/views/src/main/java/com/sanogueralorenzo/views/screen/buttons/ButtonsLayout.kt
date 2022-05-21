package com.sanogueralorenzo.views.screen.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.sanogueralorenzo.views.R
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.views.databinding.LayoutButtonsBinding
import com.sanogueralorenzo.views.extensions.gone
import com.sanogueralorenzo.views.extensions.visible

class ButtonsLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val binding: LayoutButtonsBinding by viewBinding()

    init {
        View.inflate(context, R.layout.layout_buttons, this)
    }

    fun setupButtons(buttonType: ButtonType) = buttonType.let {
        when (it) {
            is ButtonType.None -> {
                this.gone()
                binding.primaryButton.gone()
                binding.secondaryButton.gone()
            }
            is ButtonType.Single -> {
                this.visible()
                binding.primaryButton.setupButton(it.text, it.action)
                binding.secondaryButton.gone()
            }
            is ButtonType.Double -> {
                this.visible()
                binding.primaryButton.setupButton(it.primaryText, it.primaryAction)
                binding.secondaryButton.setupButton(it.secondaryText, it.secondaryAction)
            }
        }
    }

    private fun Button.setupButton(
        text: String,
        action: () -> Unit
    ) {
        this.visible()
        this.text = text
        this.setOnClickListener { action.invoke() }
    }

    companion object {
        fun create(c: Context, buttonType: ButtonType) =
            ButtonsLayout(context = c).also { it.setupButtons(buttonType) }
    }
}

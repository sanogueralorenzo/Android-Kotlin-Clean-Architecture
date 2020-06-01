package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.view_primary_button.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PrimaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_primary_button, this)
    }

    val get: MaterialButton
        get() = primaryButton

    @TextProp
    fun setText(text: CharSequence) {
        primaryButton.text = text
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        primaryButton.setOnClickListener(clickListener)
    }

    companion object {
        fun create(c: Context, text: String, clickListener: () -> Unit): PrimaryButton =
            PrimaryButton(c).apply {
                primaryButton.text = text
                primaryButton.setOnClickListener { clickListener() }
            }
    }
}

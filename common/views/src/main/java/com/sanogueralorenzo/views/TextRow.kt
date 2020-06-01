package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.sanogueralorenzo.views.extensions.toDp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TextRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    @TextProp
    fun setBody(text: CharSequence) {
        this.text = text
    }

    @ModelProp
    @JvmOverloads
    fun setBodyGravity(gravity: Int = Gravity.START) {
        this.gravity = gravity
    }

    @ModelProp
    fun setBodyColor(@ColorRes color: Int?) {
        color?.let { this.setTextColor(ContextCompat.getColor(context, it)) }
    }

    @ModelProp
    fun setPaddingStart(paddingStart: Int?) {
        paddingStart?.let { this.setPadding(toDp(it), paddingTop, paddingRight, paddingBottom) }
    }

    @ModelProp
    fun setPaddingEnd(paddingEnd: Int?) {
        paddingEnd?.let { this.setPadding(paddingLeft, paddingTop, toDp(it), paddingBottom) }
    }

    @ModelProp
    fun setStyle(style: TextStyle) {
        this.setTextAppearance(style.id)
    }

    enum class TextStyle(val id: Int) {
        HEADLINE(R.style.Headline),
        SUBTITLE(R.style.Subtitle),
        BODY(R.style.Body),
        CAPTION(R.style.Caption)
    }
}

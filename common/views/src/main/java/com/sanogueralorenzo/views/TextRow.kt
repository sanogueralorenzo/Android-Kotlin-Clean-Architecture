package com.sanogueralorenzo.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TextRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    @TextProp
    fun setTitle(text: CharSequence) {
        this.text = text
    }

    @ModelProp
    fun setStyle(style: TextStyle) {
        setTextAppearance(style.id)
    }

    enum class TextStyle(val id: Int) {
        LARGE(android.R.style.TextAppearance_Material_Large),
        MEDIUM(android.R.style.TextAppearance_Material_Medium),
        BODY(android.R.style.TextAppearance_Material_Body2)
    }
}

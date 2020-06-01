package com.sanogueralorenzo.views.textinput

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.sanogueralorenzo.views.R
import kotlinx.android.synthetic.main.view_text_input_layout.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TextInputLayoutRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_text_input_layout, this)
    }

    private var textWatcher: TextWatcher? = null

    @JvmField
    @CallbackProp
    var textChangedListener: ((String) -> Unit)? = null

    @TextProp
    fun setBody(text: CharSequence?) {
        if (text ?: "" == input.text.toString()) return
        textWatcher?.let { input.removeTextChangedListener(it) }
        input.text?.let { it.replace(0, it.length, StringBuilder(text ?: "")) }
        textWatcher?.let { input.addTextChangedListener(it) }
    }

    @TextProp
    fun setHint(text: CharSequence?) {
        inputLayout.hint = text
    }

    @TextProp
    fun setInnerHint(text: CharSequence?) {
        input.hint = text
    }

    @TextProp
    fun setHelperText(text: CharSequence?) {
        inputLayout.helperText = text
    }

    @TextProp
    fun setError(error: CharSequence?) {
        inputLayout.isErrorEnabled = error != null
        inputLayout.error = error
    }

    @ModelProp
    @JvmOverloads
    fun setInputType(inputType: Int = STANDARD) {
        input.inputType = inputType
    }

    @ModelProp
    fun setMaxLength(maxLength: Int?) {
        input.filters =
            maxLength?.let { arrayOf(InputFilter.LengthFilter(maxLength)) } ?: emptyArray()
    }

    @ModelProp
    fun setCounterLimit(limit: Int?) {
        inputLayout.counterMaxLength = limit ?: 0
        inputLayout.isCounterEnabled = limit != null
    }

    @AfterPropsSet
    fun textChangedListener() {
        textWatcher = input.internalTextChangedListener(textWatcher, textChangedListener)
    }

    // @NonEpoxyUsage
    fun setTextChangedListener(listener: (String) -> Unit) {
        textWatcher = input.internalTextChangedListener(textWatcher, listener)
    }

    companion object {
        const val STANDARD = InputType.TYPE_CLASS_TEXT or
            InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or
            InputType.TYPE_TEXT_FLAG_AUTO_CORRECT

        const val MULTI_LINE = InputType.TYPE_CLASS_TEXT or
            InputType.TYPE_TEXT_FLAG_MULTI_LINE or
            InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or
            InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
    }
}

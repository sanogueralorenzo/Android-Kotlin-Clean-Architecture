package com.sanogueralorenzo.views.textinput

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.google.android.material.textfield.TextInputLayout
import com.sanogueralorenzo.views.R
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.views.databinding.ViewTextInputLayoutBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TextInputLayoutRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewTextInputLayoutBinding by viewBinding()

    init {
        inflate(context, R.layout.view_text_input_layout, this)
    }

    private var textWatcher: TextWatcher? = null

    var textChangedListener: ((String) -> Unit)? = null
        @CallbackProp set

    val text: String
        get() = editText.text.toString()

    val editText: EditText
        get() = binding.input

    val textInputLayout: TextInputLayout
        get() = binding.inputLayout

    @TextProp
    fun setBody(text: CharSequence?) {
        if (text ?: "" == binding.input.text.toString()) return
        textWatcher?.let { binding.input.removeTextChangedListener(it) }
        binding.input.text?.let { it.replace(0, it.length, StringBuilder(text ?: "")) }
        textWatcher?.let { binding.input.addTextChangedListener(it) }
    }

    @TextProp
    fun setHint(text: CharSequence?) {
        binding.inputLayout.hint = text
    }

    @TextProp
    fun setInnerHint(text: CharSequence?) {
        binding.input.hint = text
    }

    @TextProp
    fun setHelperText(text: CharSequence?) {
        binding.inputLayout.helperText = text
    }

    @TextProp
    fun setError(error: CharSequence?) {
        binding.inputLayout.isErrorEnabled = error != null
        binding.inputLayout.error = error
    }

    @ModelProp
    @JvmOverloads
    fun setInputType(inputType: Int = STANDARD) {
        binding.input.inputType = inputType
    }

    @ModelProp
    fun setMaxLength(maxLength: Int?) {
        binding.input.filters =
            maxLength?.let { arrayOf(InputFilter.LengthFilter(maxLength)) } ?: emptyArray()
    }

    @ModelProp
    fun setCounterLimit(limit: Int?) {
        binding.inputLayout.counterMaxLength = limit ?: 0
        binding.inputLayout.isCounterEnabled = limit != null
    }

    @AfterPropsSet
    fun afterPropsSet() {
        textWatcher = binding.input.internalTextChangedListener(textWatcher, textChangedListener)
    }

    // @NonEpoxyUsage
    fun textChangedListener(listener: (String) -> Unit) {
        textWatcher = binding.input.internalTextChangedListener(textWatcher, listener)
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

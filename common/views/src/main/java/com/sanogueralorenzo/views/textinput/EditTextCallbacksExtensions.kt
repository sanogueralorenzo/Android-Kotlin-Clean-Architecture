package com.sanogueralorenzo.views.textinput

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

internal fun EditText.internalTextChangedListener(
    textWatcher: TextWatcher?,
    callback: ((String) -> Unit)?
): TextWatcher? {
    textWatcher?.let { removeTextChangedListener(it) }
    if (callback != null) {
        val internalWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                Unit

            override fun afterTextChanged(s: Editable?) {
                callback(s.toString())
            }
        }
        addTextChangedListener(internalWatcher)
        return internalWatcher
    } else {
        return null
    }
}

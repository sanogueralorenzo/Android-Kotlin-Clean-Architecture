package com.sanogueralorenzo.sample.presentation.search.view

import android.text.InputType
import android.view.inputmethod.EditorInfo
import com.google.android.material.textfield.TextInputLayout
import com.sanogueralorenzo.sample.R
import com.sanogueralorenzo.views.Keyboard
import com.sanogueralorenzo.views.textinput.TextInputLayoutRow

/**
 * Uses Text Input from views module and extends it to avoid having to duplicate XMLs
 */
fun TextInputLayoutRow.searchInput(listener: (String) -> Unit) {
    textInputLayout.hint = context.getString(R.string.search)
    textInputLayout.setEndIconDrawable(R.drawable.ic_search)
    textInputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
    textInputLayout.setEndIconTintList(null)

    editText.setOnEditorActionListener { _, _, _ ->
        searchClick(
            this,
            listener
        )
        true
    }
    editText.isSingleLine = true
    editText.imeOptions = EditorInfo.IME_ACTION_SEARCH
    editText.inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
    textInputLayout.setEndIconOnClickListener {
        searchClick(
            this,
            listener
        )
    }
}

private fun searchClick(view: TextInputLayoutRow, listener: (String) -> Unit) {
    Keyboard.hide(view)
    if (view.text.isBlank()) return
    listener(view.text)
    view.editText.text!!.clear()
}

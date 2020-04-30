package com.sanogueralorenzo.views.textinput

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.view_text_input_layout.view.*

val TextInputLayoutRow.text: String
    get() = editText.text.toString()

val TextInputLayoutRow.editText: TextInputEditText
    get() = input

val TextInputLayoutRow.textInputLayout: TextInputLayout
    get() = inputLayout

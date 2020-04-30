package com.sanogueralorenzo.views.screen.buttons

sealed class ButtonType {

    object None : ButtonType()

    data class Single(val text: String, val action: () -> Unit) : ButtonType()

    data class Double(
        val primaryText: String,
        val primaryAction: () -> Unit,
        val secondaryText: String,
        val secondaryAction: () -> Unit
    ) : ButtonType()
}

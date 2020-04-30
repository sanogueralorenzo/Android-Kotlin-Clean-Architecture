package com.sanogueralorenzo.views

import android.view.View
import com.google.android.material.snackbar.Snackbar

object ErrorDisplay {

    fun create(view: View, error: Throwable, retry: (() -> Unit)? = null): Snackbar =
        Snackbar.make(
            view,
            error.message!!,
            if (retry != null) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
        ).apply {
            if (retry != null) {
                this.setAction(view.context.getText(R.string.retry)) { retry.invoke() }
            }
        }

    fun show(view: View, error: Throwable, retry: (() -> Unit)? = null) =
        create(view, error, retry).show()
}

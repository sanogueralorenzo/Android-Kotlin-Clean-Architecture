package com.sanogueralorenzo.views.extensions

import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

fun TextView.setTextWithLinks(originalText: String, vararg links: Pair<String, () -> Unit>) {
    text = originalText
    SpannableString(text).let {
        for (link in links) {
            val startIndexOfLink = text.toString().indexOf(link.first)
            it.setSpan(
                clickableSpan(link.second), startIndexOfLink, startIndexOfLink + link.first.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        movementMethod = LinkMovementMethod.getInstance()
        setText(it, TextView.BufferType.SPANNABLE)
    }
}

private fun clickableSpan(callback: () -> Unit): ClickableSpan = object : ClickableSpan() {
    override fun onClick(view: View) {
        callback()
    }
}

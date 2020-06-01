@file:Suppress("DEPRECATION", "MagicNumber")

package com.sanogueralorenzo.views.extensions

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun sendIntent(text: String) = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

fun urlIntent(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url))

internal fun CircularProgressDrawable.style() {
    strokeWidth = 8f
    centerRadius = 45f
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(Color.GRAY, BlendMode.SRC_ATOP)
    } else {
        this.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
    }
    start()
}

fun SwipeRefreshLayout.enable() {
    isEnabled = true
    setColorSchemeColors(Color.GRAY)
}

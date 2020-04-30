@file:Suppress("DEPRECATION", "MagicNumber")

package com.sanogueralorenzo.views.extensions

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_container.*

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun FragmentActivity.replaceFragment(fragment: Fragment, backstack: Boolean) =
    supportFragmentManager
        .beginTransaction()
        .replace(android.R.id.content, fragment)
        .also { if (backstack) it.addToBackStack(null) }
        .commit()

fun SwipeRefreshLayout.setInitialRefresh() {
        isEnabled = true
        setColorSchemeColors(Color.GRAY)
}

fun CircularProgressDrawable.style() {
    strokeWidth = 8f
    centerRadius = 45f
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(Color.GRAY, BlendMode.SRC_ATOP)
    } else {
        this.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
    }
    start()
}

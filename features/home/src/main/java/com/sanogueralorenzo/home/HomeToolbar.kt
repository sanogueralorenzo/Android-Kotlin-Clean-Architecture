package com.sanogueralorenzo.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_home_toolbar.view.*

class HomeToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_home_toolbar, this)
    }

    fun onPageSelected(position: Int) = when (position) {
        0 -> selectToolbarItem(goals, calendar, profile)
        1 -> selectToolbarItem(calendar, goals, profile)
        else -> selectToolbarItem(profile, goals, calendar)
    }

    private fun selectToolbarItem(enabled: View, vararg disabled: View) {
        enabled.alpha = ENABLED
        for (v in disabled) v.alpha = DISABLED
    }

    fun onGoalsClick(callback: () -> Unit) = goals.setOnClickListener { callback() }
    fun onCalendarClick(callback: () -> Unit) = calendar.setOnClickListener { callback() }
    fun onProfileClick(callback: () -> Unit) = profile.setOnClickListener { callback() }

    private companion object {
        const val ENABLED = 1F
        const val DISABLED = 0.5F
    }
}

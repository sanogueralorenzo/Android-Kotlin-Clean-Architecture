package com.sanogueralorenzo.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.sanogueralorenzo.home.databinding.ViewHomeToolbarBinding
import com.sanogueralorenzo.views.binding.viewBinding

class HomeToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewHomeToolbarBinding by viewBinding()

    init {
        View.inflate(context, R.layout.view_home_toolbar, this)
    }

    fun onPageSelected(position: Int) = when (position) {
        0 -> selectToolbarItem(binding.goals, binding.calendar, binding.profile)
        1 -> selectToolbarItem(binding.calendar, binding.goals, binding.profile)
        else -> selectToolbarItem(binding.profile, binding.goals, binding.calendar)
    }

    private fun selectToolbarItem(enabled: View, vararg disabled: View) {
        enabled.alpha = ENABLED
        for (v in disabled) v.alpha = DISABLED
    }

    fun onGoalsClick(callback: () -> Unit) = binding.goals.setOnClickListener { callback() }
    fun onCalendarClick(callback: () -> Unit) = binding.calendar.setOnClickListener { callback() }
    fun onProfileClick(callback: () -> Unit) = binding.profile.setOnClickListener { callback() }

    private companion object {
        const val ENABLED = 1F
        const val DISABLED = 0.5F
    }
}

package com.sanogueralorenzo.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sanogueralorenzo.home.databinding.FragmentHomeBinding
import com.sanogueralorenzo.navigation.features.ProfileNavigation
import com.sanogueralorenzo.views.binding.viewBinding
import com.sanogueralorenzo.views.extensions.onFragmentBackCallback
import com.sanogueralorenzo.views.extensions.sendIntent
import com.sanogueralorenzo.views.screen.removeOverScroll

/** @see com.sanogueralorenzo.navigation.features.HomeNavigation.home */
@Suppress("Unused")
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.removeOverScroll()
        binding.viewPager.adapter = stateAdapter()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // appBarLayout.setExpanded(true, true)
                binding.homeToolbar.onPageSelected(position)
                animateFabPageSelected(position)
            }
        })
        binding.homeToolbar.onGoalsClick { binding.viewPager.currentItem = GOAL_POSITION }
        binding.homeToolbar.onCalendarClick { binding.viewPager.currentItem = CALENDAR_POSITION }
        binding.homeToolbar.onProfileClick { binding.viewPager.currentItem = PROFILE_POSITION }
        binding.fab.setOnClickListener {
            when (binding.viewPager.currentItem) {
                GOAL_POSITION -> { }
                CALENDAR_POSITION -> { }
                else -> startActivity(
                    Intent.createChooser(
                        sendIntent(getString(R.string.share_app_text)), null
                    )
                )
            }
        }

        onFragmentBackCallback(
            callback = { binding.viewPager.currentItem = GOAL_POSITION },
            predicate = { binding.viewPager.currentItem != GOAL_POSITION }
        )
    }

    private fun animateFabPageSelected(position: Int) = when (position) {
        GOAL_POSITION -> binding.fab.animate(R.drawable.ic_add)
        CALENDAR_POSITION -> binding.fab.animate(R.drawable.ic_search)
        else -> binding.fab.animate(R.drawable.ic_share)
    }

    private fun FloatingActionButton.animate(@DrawableRes image: Int) {
        this.scale(0F).withEndAction {
            this.setImageDrawable(ContextCompat.getDrawable(requireContext(), image))
            this.scale(1F).start()
        }.start()
    }

    private fun FloatingActionButton.scale(value: Float): ViewPropertyAnimator =
        this.animate().scaleX(value).scaleY(value).setDuration(FAB_SCALE_DURATION)

    private fun stateAdapter(): FragmentStateAdapter = object : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment = when (position) {
            GOAL_POSITION -> GoalFragment()
            CALENDAR_POSITION -> DummyFragment()
            PROFILE_POSITION -> ProfileNavigation.profile()
            else -> error("unhandled position")
        }
    }

    private companion object {
        const val GOAL_POSITION = 0
        const val CALENDAR_POSITION = 1
        const val PROFILE_POSITION = 2
        const val FAB_SCALE_DURATION = 100L
    }
}

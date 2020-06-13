package com.sanogueralorenzo.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sanogueralorenzo.navigation.features.ProfileNavigation
import com.sanogueralorenzo.views.extensions.onFragmentBackCallback
import com.sanogueralorenzo.views.screen.ViewPagerFragment

/**
 * @see com.sanogueralorenzo.navigation.features.HomeNavigation.homeFragment
 */
@Suppress("Unused")
class HomeFragment : ViewPagerFragment() {

    override fun stateAdapter(): FragmentStateAdapter = object : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment = ProfileNavigation.name()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onFragmentBackCallback(
            callback = { viewPager.setCurrentItem(0, true) },
            predicate = { viewPager.currentItem != 0 }
        )
    }
}

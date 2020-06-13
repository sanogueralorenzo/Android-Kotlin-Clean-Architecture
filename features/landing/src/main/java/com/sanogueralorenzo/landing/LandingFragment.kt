package com.sanogueralorenzo.landing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sanogueralorenzo.navigation.core.replaceFragment
import com.sanogueralorenzo.navigation.features.HomeNavigation
import com.sanogueralorenzo.views.extensions.onFragmentBackCallback
import kotlinx.android.synthetic.main.fragment_landing.*

/**
 * @see com.sanogueralorenzo.navigation.features.LandingNavigation.landingIntent
 */
@Suppress("Unused")
class LandingFragment : Fragment(R.layout.fragment_landing) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView.apply {
                setOnNavigationItemSelectedListener {
                    if (bottomNavigationView.selectedItemId != it.itemId) {
                        when (it.itemId) {
                            R.id.navigation_home -> navigateTo(HomeNavigation.homeFragment())
                            R.id.navigation_search -> { }
                            R.id.navigation_add -> { }
                            R.id.navigation_notifications -> { }
                            R.id.navigation_profile -> { }
                            else -> error("unhandled bottom navigation item")
                        }
                    }
                    true
                }
        }

        // onBackPressed select home if not selected, otherwise just close
        onFragmentBackCallback(
            callback = { bottomNavigationView.selectedItemId = R.id.navigation_home },
            predicate = { bottomNavigationView.selectedItemId != R.id.navigation_home }
        )

        if (savedInstanceState != null) return
        navigateTo(HomeNavigation.homeFragment())
    }

    private fun navigateTo(fragment: Fragment) =
        replaceFragment(fragment, backstack = false, containerId = landingContainer.id)
}

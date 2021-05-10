package com.sanogueralorenzo.profile

import com.sanogueralorenzo.navigation.extensions.replaceFragment
import com.sanogueralorenzo.navigation.features.ProfileNavigation
import com.sanogueralorenzo.views.screen.ContainerFragment
import com.sanogueralorenzo.views.screen.simpleController
import com.sanogueralorenzo.views.secondaryButton

/** @see com.sanogueralorenzo.navigation.features.ProfileNavigation.profile */
@Suppress("Unused")
class ProfileFragment : ContainerFragment() {
    override fun controller() = simpleController {
        profileHeaderRow {
            id("header")
            name("Mario")
            goals("32 goals completed!")
        }

        secondaryButton {
            id("name")
            text(R.string.name)
            clickListener { _ -> replaceFragment(ProfileNavigation.fragment(NameFragment::class)) }
        }
    }
}

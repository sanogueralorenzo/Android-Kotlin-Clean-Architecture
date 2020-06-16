package com.sanogueralorenzo.navigation.features

import com.sanogueralorenzo.navigation.core.Navigator

object ProfileNavigation : Navigator() {
    fun profile() = fragment("com.sanogueralorenzo.profile.ProfileFragment")
}

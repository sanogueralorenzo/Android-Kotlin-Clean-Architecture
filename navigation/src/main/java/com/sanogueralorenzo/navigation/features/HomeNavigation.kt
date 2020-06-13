package com.sanogueralorenzo.navigation.features

import com.sanogueralorenzo.navigation.core.Navigator

object HomeNavigation : Navigator() {
    fun homeFragment() = fragment("com.sanogueralorenzo.home.HomeFragment")
}

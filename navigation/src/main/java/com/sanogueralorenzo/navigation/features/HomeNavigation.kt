package com.sanogueralorenzo.navigation.features

import com.sanogueralorenzo.navigation.core.Navigator

object HomeNavigation : Navigator() {
    fun homeIntent() = fragmentIntent("com.sanogueralorenzo.home.HomeFragment")
}

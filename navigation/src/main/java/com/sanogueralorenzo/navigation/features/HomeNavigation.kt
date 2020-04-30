package com.sanogueralorenzo.navigation.features

import com.sanogueralorenzo.navigation.core.Fragments
import com.sanogueralorenzo.navigation.core.loadIntent

object HomeNavigation : Fragments("com.sanogueralorenzo.home") {

    //    fun home() = HomeNavigation.fragmentIntent("HomeActivity")
    fun home() = loadIntent("com.sanogueralorenzo.home.HomeActivity")
}

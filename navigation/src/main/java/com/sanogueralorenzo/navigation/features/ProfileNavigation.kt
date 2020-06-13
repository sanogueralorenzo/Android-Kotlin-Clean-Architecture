package com.sanogueralorenzo.navigation.features

import com.sanogueralorenzo.navigation.core.Navigator

object ProfileNavigation : Navigator() {
    fun name() = fragment("com.sanogueralorenzo.profile.NameFragment")
}

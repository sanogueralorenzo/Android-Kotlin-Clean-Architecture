package com.sanogueralorenzo.navigation.features

import android.content.Intent
import com.sanogueralorenzo.navigation.core.Navigator

object HomeNavigation : Navigator() {
    fun home() = fragmentIntent("com.sanogueralorenzo.home.HomeFragment")
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
}

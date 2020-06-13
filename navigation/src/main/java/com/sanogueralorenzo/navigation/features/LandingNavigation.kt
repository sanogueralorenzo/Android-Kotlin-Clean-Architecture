package com.sanogueralorenzo.navigation.features

import android.content.Intent
import com.sanogueralorenzo.navigation.core.Navigator

object LandingNavigation : Navigator() {
    fun landingIntent() = fragmentIntent("com.sanogueralorenzo.landing.LandingFragment")
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
}

package com.sanogueralorenzo.navigation.features

import android.content.Intent
import com.sanogueralorenzo.navigation.loadIntentOrNull

object HomeNavigation : DynamicFeature<Intent> {

    private const val HOME = "com.sanogueralorenzo.home.HomeActivity"

    override val dynamicStart: Intent?
        get() = HOME.loadIntentOrNull()
}

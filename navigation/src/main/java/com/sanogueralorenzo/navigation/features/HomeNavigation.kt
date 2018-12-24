package com.sanogueralorenzo.navigation.features

import android.content.Intent
import com.sanogueralorenzo.navigation.loadIntentOrNull

object HomeNavigation : DynamicFeature<Intent> {

    private const val HOME_ENTRY_POINT = "com.sanogueralorenzo.home.HomeActivity"

    override val dynamicStart: Intent?
        get() = HOME_ENTRY_POINT.loadIntentOrNull()
}

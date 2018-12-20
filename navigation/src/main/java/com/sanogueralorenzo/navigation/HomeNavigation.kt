package com.sanogueralorenzo.navigation

import android.content.Intent

object HomeNavigation : DynamicFeature {

    override val dynamicIntent: Intent?
        get() = loadIntentOrNull("com.sanogueralorenzo.home.HomeActivity")
}

package com.sanogueralorenzo.navigation

import android.content.Intent

object LoginNavigation : DynamicFeature {

    override val dynamicIntent: Intent?
        get() = loadIntentOrNull("com.sanogueralorenzo.login.LoginActivity")
}

package com.sanogueralorenzo.navigation.features

import android.content.Intent
import com.sanogueralorenzo.navigation.loadIntentOrNull

object LoginNavigation : DynamicFeature<Intent> {

    private const val LOGIN = "com.sanogueralorenzo.login.LoginActivity"

    override val dynamicStart: Intent?
        get() = LOGIN.loadIntentOrNull()
}

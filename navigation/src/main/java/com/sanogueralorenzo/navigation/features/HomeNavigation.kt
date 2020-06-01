package com.sanogueralorenzo.navigation.features

import android.content.Intent
import com.sanogueralorenzo.navigation.core.ClassRegistry
import com.sanogueralorenzo.navigation.core.Navigator

object HomeNavigation : Navigator() {
    fun homeIntent() = ClassRegistry.loadIntent(packageName, "com.sanogueralorenzo.home.HomeActivity")
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

    fun habitsFragment() = fragment("com.sanogueralorenzo.habits.HabitsFragment")
}

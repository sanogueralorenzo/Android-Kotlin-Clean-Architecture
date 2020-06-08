package com.sanogueralorenzo.navigation.features

import android.content.Intent
import com.sanogueralorenzo.navigation.core.Navigator

object HomeNavigation : Navigator() {
    fun homeIntent() = fragmentIntent("com.sanogueralorenzo.home.HomeFragment")
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

    fun habitsFragment() = fragment("com.sanogueralorenzo.habits.HabitsFragment")
}

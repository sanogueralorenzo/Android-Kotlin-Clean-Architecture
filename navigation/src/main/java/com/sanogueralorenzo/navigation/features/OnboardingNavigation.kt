package com.sanogueralorenzo.navigation.features

import com.sanogueralorenzo.navigation.core.Navigator

object OnboardingNavigation : Navigator() {
    fun intro() = fragmentIntent("com.sanogueralorenzo.onboarding.IntroFragment")
}

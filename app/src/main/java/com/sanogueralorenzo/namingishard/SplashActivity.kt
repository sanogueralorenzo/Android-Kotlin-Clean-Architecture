package com.sanogueralorenzo.namingishard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.navigation.features.HomeNavigation
import com.sanogueralorenzo.navigation.features.OnboardingNavigation
import com.sanogueralorenzo.usermanager.UserManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        when {
            UserManager().newUser -> startActivity(OnboardingNavigation.intro())
            else -> startActivity(HomeNavigation.home())
        }
        finish()
    }
}

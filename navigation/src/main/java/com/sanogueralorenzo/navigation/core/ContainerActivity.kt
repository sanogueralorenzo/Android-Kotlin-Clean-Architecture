package com.sanogueralorenzo.navigation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.navigation.extensions.replaceFragment

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) loadFragment()
    }

    private fun loadFragment() {
        replaceFragment(
            fragment = ClassRegistry.loadFragment(
                intent.getStringExtra(FRAGMENT_NAME)!!,
                intent.getBundleExtra(FRAGMENT_BUNDLE)
            ),
            backstack = false
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
    }

    companion object {
        const val FRAGMENT_NAME = "fragment_name"
        const val FRAGMENT_BUNDLE = "fragment_bundle"
    }
}

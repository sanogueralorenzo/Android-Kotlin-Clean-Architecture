package com.sanogueralorenzo.sample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.navigation.extensions.replaceFragment
import com.sanogueralorenzo.sample.presentation.search.GifsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) return
        replaceFragment(GifsFragment(), backstack = false)
    }
}

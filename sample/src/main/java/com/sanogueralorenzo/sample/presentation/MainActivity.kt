package com.sanogueralorenzo.sample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.navigation.features.GifsNavigation
import com.sanogueralorenzo.sample.presentation.search.GifsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        startActivity(GifsNavigation.fragmentIntent(GifsFragment::class))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
    }
}

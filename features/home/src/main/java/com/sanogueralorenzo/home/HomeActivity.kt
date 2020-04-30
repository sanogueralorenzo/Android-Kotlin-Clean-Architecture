package com.sanogueralorenzo.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                }
                R.id.navigation_dashboard -> {
                }
                R.id.navigation_notifications -> {
                }
                else -> error("missing navigation item selected listener")
            }
            true
        }
    }
}

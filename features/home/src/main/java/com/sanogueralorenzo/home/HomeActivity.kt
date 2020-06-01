package com.sanogueralorenzo.home

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        bottomNavigationView.apply {
            itemTextColor = ColorStateList.valueOf(getColor(R.color.colorBackgroundInverse))
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_journal -> { }
                    R.id.navigation_habits -> { }
                    R.id.navigation_zoneout -> { }
                    else -> error("unhandled bottom navigation item")
                }
                true
            }
        }
        if (savedInstanceState != null) return
        // Selecting center habits by default on initial creation
        bottomNavigationView.selectedItemId = R.id.navigation_habits
        // replaceFragment(HomeNavigation.habitsFragment(), backstack = false, containerId = homeContainer.id)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Implement double click to exit with toast
        // Implement proper back stack?
    }
}

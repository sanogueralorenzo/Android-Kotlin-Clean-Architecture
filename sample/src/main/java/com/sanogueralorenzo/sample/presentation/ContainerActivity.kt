package com.sanogueralorenzo.sample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.sample.presentation.search.GifsFragment
import com.sanogueralorenzo.views.extensions.replaceFragment

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // most basic container, if saved instance not null we let android restore the fragment for us
        if (savedInstanceState != null) return
        replaceFragment(GifsFragment(), false)
    }
}

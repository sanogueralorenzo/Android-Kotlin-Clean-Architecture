package com.sanogueralorenzo.namingishard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.navigation.features.SampleNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPosts()
    }

    private fun startPosts() = SampleNavigation.dynamicStart?.let { startActivity(it) }
}

package com.sanogueralorenzo.namingishard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.navigation.features.HomeNavigation
import com.sanogueralorenzo.navigation.features.LoginNavigation
import com.sanogueralorenzo.navigation.features.SampleNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startLogin()
    }

    private fun startLogin() = LoginNavigation.dynamicStart?.let {
        startActivityForResult(it, LOGIN)
    }

    private fun startHome() = HomeNavigation.dynamicStart?.let {
        startActivity(it)
    }

    private fun startPosts() = SampleNavigation.dynamicStart?.let {
        startActivity(it)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == LOGIN && resultCode == Activity.RESULT_OK -> startHome()
            else -> startPosts()
        }
        finish()
    }

    companion object {
        private const val LOGIN = 100
    }
}

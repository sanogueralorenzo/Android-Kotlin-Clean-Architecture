package com.sanogueralorenzo.namingishard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.navigation.HomeNavigation
import com.sanogueralorenzo.navigation.LoginNavigation
import com.sanogueralorenzo.navigation.PostsNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startLogin()
    }

    private fun startLogin() = LoginNavigation.dynamicIntent?.let {
        startActivityForResult(it, LOGIN)
    }


    private fun startHome() = HomeNavigation.dynamicIntent?.let {
        startActivity(it)
    }

    private fun startPosts() = PostsNavigation.dynamicIntent?.let {
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

package com.sanogueralorenzo.namingishard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.home.HomeActivity
import com.sanogueralorenzo.login.LoginActivity
import com.sanogueralorenzo.navigation.PostsNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startLogin()
    }

    private fun startLogin() =
        startActivityForResult(Intent(this, LoginActivity::class.java), LOGIN)

    private fun startHome() =
        startActivity(Intent(this, HomeActivity::class.java)).also { finish() }

    private fun startPosts() =
        startActivity(PostsNavigation.dynamicIntent)

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

package com.sanogueralorenzo.namingishard

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.home.HomeActivity
import com.sanogueralorenzo.login.LoginActivity
import com.sanogueralorenzo.posts.presentation.postlist.PostListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPosts()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun startLogin() =
        startActivityForResult(Intent(this, LoginActivity::class.java), LOGIN)

    private fun startHome() =
        startActivity(Intent(this, HomeActivity::class.java)).also { finish() }

    private fun startPosts() =
        startActivity(Intent(this, PostListActivity::class.java)).also { finish() }

    companion object {
        private const val LOGIN = 100
    }
}

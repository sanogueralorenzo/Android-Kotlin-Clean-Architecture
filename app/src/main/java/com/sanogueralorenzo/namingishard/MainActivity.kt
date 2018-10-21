package com.sanogueralorenzo.namingishard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanogueralorenzo.home.HomeActivity
import com.sanogueralorenzo.login.LoginActivity

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        startHome()
    }

    companion object {
        private const val LOGIN = 100
    }
}

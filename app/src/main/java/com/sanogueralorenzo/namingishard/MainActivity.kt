package com.sanogueralorenzo.namingishard

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sanogueralorenzo.home.HomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startHome()
    }

    private fun startHome() =
        startActivity(Intent(this, HomeActivity::class.java)).also { finish() }
}

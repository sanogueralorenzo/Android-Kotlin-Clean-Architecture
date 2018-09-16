package com.sanogueralorenzo.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}

//interface MapperContext {
//    val myDependency: String
//
//    fun Int.toPresentationModel() = myDependency + this
//}
//
//data class MyViewModel(): MapperContext {
//    override fun Int.toPresentationModel(): String {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}

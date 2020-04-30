package com.sanogueralorenzo.namingishard

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.MvRxViewModelConfigFactory
import com.sanogueralorenzo.cache.CacheLibrary
// import org.koin.android.ext.koin.androidContext
// import org.koin.core.context.startKoin
import kotlin.random.Random

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MvRx.viewModelConfigFactory = MvRxViewModelConfigFactory(applicationContext)
        // Cache initialization to allow saving into device
        CacheLibrary.init(this)
        // Koin DI initialization
        // startKoin { androidContext(applicationContext) }

        // Random nightMode to make developer aware of day/night themes
        val nightMode = when (Random.nextBoolean()) {
            true -> AppCompatDelegate.MODE_NIGHT_YES
            false -> AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}

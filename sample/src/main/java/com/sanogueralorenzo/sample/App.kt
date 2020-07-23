package com.sanogueralorenzo.sample

import android.app.Application
import com.airbnb.mvrx.MavericksViewModelConfigFactory
import com.airbnb.mvrx.MvRx
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        MvRx.viewModelConfigFactory = MavericksViewModelConfigFactory(applicationContext)
    }
}

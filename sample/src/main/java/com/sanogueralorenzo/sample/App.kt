package com.sanogueralorenzo.sample

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksViewModelConfigFactory
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        Mavericks.viewModelConfigFactory = MavericksViewModelConfigFactory(applicationContext)
    }
}

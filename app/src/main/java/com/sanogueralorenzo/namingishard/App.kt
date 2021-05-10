package com.sanogueralorenzo.namingishard

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksViewModelConfigFactory
import com.sanogueralorenzo.cache.CacheLibrary
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks())
        Mavericks.viewModelConfigFactory = MavericksViewModelConfigFactory(applicationContext)
        CacheLibrary.init(this)
    }
}

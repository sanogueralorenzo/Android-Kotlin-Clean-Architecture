package com.sanogueralorenzo.namingishard

import android.app.Application
import com.sanogueralorenzo.cache.CacheLibrary
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        // Enable Strict mode? https://developer.android.com/reference/android/os/StrictMode
        super.onCreate()
        when {
            LeakCanary.isInAnalyzerProcess(this) -> return
            // Report Leaks to Firebase Crashlytics? :thinking:
            // https://github.com/square/leakcanary/wiki/Customizing-LeakCanary#uploading-to-a-server
            else -> LeakCanary.install(this)
        }

        // Unique initialization of Cache library to allow saving into device
        CacheLibrary.init(this)

        startKoin { androidContext(this@App) }
    }
}

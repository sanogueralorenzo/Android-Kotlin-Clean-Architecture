package com.sanogueralorenzo.presentation

import android.app.Application
import com.pacoworks.rxpaper2.RxPaperBook
import com.sanogueralorenzo.presentation.injection.component.DaggerInjector
import com.sanogueralorenzo.presentation.injection.component.Injector
import com.sanogueralorenzo.presentation.injection.module.AppModule
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App : Application() {

    lateinit var injector: Injector private set

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initRxPaper()
        initRxJavaPluginsErrorHandler()
    }

    private fun initDagger() {
        injector = DaggerInjector
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initRxPaper() = RxPaperBook.init(this)

    private fun initRxJavaPluginsErrorHandler() {
        RxJavaPlugins.setErrorHandler { Timber.e(it) }
    }

}

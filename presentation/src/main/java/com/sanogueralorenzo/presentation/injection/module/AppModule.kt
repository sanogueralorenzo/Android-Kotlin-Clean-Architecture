package com.sanogueralorenzo.presentation.injection.module

import com.sanogueralorenzo.presentation.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app
}

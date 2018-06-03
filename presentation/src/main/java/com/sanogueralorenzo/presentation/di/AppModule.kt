package com.sanogueralorenzo.presentation.di

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

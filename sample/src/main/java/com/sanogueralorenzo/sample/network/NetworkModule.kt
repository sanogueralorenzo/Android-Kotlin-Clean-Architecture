package com.sanogueralorenzo.sample.network

import com.sanogueralorenzo.network.createNetworkClient
import com.sanogueralorenzo.sample.BuildConfig
import com.sanogueralorenzo.sample.datasource.remote.GifService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    // TODO Move this key to NDK module so it is bundled in a .so (harder to decompile)
    const val GIPHY_API_KEY = "GxXs7nOJbG0IA5CLXDKCy0gmTeqDRz6v"

    @Provides
    @Singleton
    internal fun providesRetrofit(): Retrofit =
        createNetworkClient("https://api.giphy.com/v1/", BuildConfig.DEBUG)
            .build()

    @Provides
    @Singleton
    internal fun provideGifService(retrofit: Retrofit): GifService =
        retrofit.create(GifService::class.java)
}

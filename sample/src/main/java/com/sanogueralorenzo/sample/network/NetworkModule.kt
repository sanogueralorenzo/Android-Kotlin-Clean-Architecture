package com.sanogueralorenzo.sample.network

import com.sanogueralorenzo.network.createNetworkClient
import com.sanogueralorenzo.sample.BuildConfig
import com.sanogueralorenzo.sample.datasource.remote.GifService

object NetworkModule {

    // TODO Move this key to NDK module so it is bundled in a .so (harder to decompile)
    const val GIPHY_API_KEY = "GxXs7nOJbG0IA5CLXDKCy0gmTeqDRz6v"

    internal fun provideGifService(): GifService =
        createNetworkClient("https://api.giphy.com/v1/", BuildConfig.DEBUG)
            .build()
            .create(GifService::class.java)
}

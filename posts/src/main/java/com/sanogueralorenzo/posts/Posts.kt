package com.sanogueralorenzo.posts

import org.koin.core.context.loadKoinModules

/**
 * To be used by main application
 */
object Posts {
    fun init() {
        loadKoinModules(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            networkModule,
            cacheModule
        )
    }
}

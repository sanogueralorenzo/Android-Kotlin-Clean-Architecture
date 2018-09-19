package com.sanogueralorenzo.posts

import org.koin.standalone.StandAloneContext.loadKoinModules

/**
 * To be used by main application
 */
object Posts {
    fun init() {
        loadKoinModules(
            postListModule,
            postDetailsModule,
            repositoryModule,
            networkModule,
            cacheModule
        )
    }
}

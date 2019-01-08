package com.sanogueralorenzo.posts

import org.koin.core.context.loadKoinModules

object Posts {
    fun init() = loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule,
        cacheModule
    )
}

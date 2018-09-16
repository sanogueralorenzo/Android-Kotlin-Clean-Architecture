package com.sanogueralorenzo.posts

import android.content.Context
import com.pacoworks.rxpaper2.RxPaperBook
import org.koin.standalone.StandAloneContext.loadKoinModules

/**
 * To be used by main application
 */
object Posts {
    fun init(context: Context) {
        RxPaperBook.init(context)
        loadKoinModules(
            postListModule,
            postDetailsModule,
            repositoryModule,
            networkModule,
            cacheModule
        )
    }
}

package com.sanogueralorenzo.presentation.injection.component

import com.sanogueralorenzo.presentation.injection.module.AppModule
import com.sanogueralorenzo.presentation.injection.module.NetworkModule
import com.sanogueralorenzo.presentation.injection.module.RepositoryModule
import com.sanogueralorenzo.presentation.postdetails.PostDetailsActivity
import com.sanogueralorenzo.presentation.postlist.PostListActivity
import com.sanogueralorenzo.presentation.userdetails.UserDetailsActivity
import com.sanogueralorenzo.presentation.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (ViewModelModule::class), (NetworkModule::class), (RepositoryModule::class)])
interface Injector {
    fun inject(activity: PostListActivity)
    fun inject(activity: UserDetailsActivity)
    fun inject(activity: PostDetailsActivity)
}

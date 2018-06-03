package com.sanogueralorenzo.presentation.di

import com.sanogueralorenzo.data.di.NetworkModule
import com.sanogueralorenzo.data.di.RepositoryModule
import com.sanogueralorenzo.presentation.postdetails.PostDetailsActivity
import com.sanogueralorenzo.presentation.postlist.PostListActivity
import com.sanogueralorenzo.presentation.userdetails.UserDetailsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (RepositoryModule::class), (ViewModelModule::class)])
interface Injector {
    fun inject(activity: PostListActivity)
    fun inject(activity: UserDetailsActivity)
    fun inject(activity: PostDetailsActivity)
}

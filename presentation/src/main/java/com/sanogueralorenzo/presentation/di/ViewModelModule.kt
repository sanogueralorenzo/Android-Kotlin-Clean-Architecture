package com.sanogueralorenzo.presentation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sanogueralorenzo.presentation.ViewModelFactory
import com.sanogueralorenzo.presentation.ViewModelKey
import com.sanogueralorenzo.presentation.postdetails.PostDetailsViewModel
import com.sanogueralorenzo.presentation.postlist.PostListViewModel
import com.sanogueralorenzo.presentation.userdetails.UserDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    internal abstract fun postListViewModel(viewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailsViewModel::class)
    internal abstract fun postDetailsViewModel(viewModel: PostDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    internal abstract fun userDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel

    //Add ViewModels here

}

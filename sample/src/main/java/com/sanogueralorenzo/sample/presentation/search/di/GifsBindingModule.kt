package com.sanogueralorenzo.sample.presentation.search.di

import com.sanogueralorenzo.sample.presentation.search.GifsViewModel
import com.sanogueralorenzo.sample.presentation.search.GifsViewModel_AssistedFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class GifsBindingModule {
    @Binds
    abstract fun viewModel(factory: GifsViewModel_AssistedFactory): GifsViewModel.Factory
}

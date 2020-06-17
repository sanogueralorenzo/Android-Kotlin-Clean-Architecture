package com.sanogueralorenzo.sample.presentation.search

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class GifsModule {
    @Binds
    abstract fun viewModel(factory: GifsViewModel_AssistedFactory): GifsViewModel.Factory
}

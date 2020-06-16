package com.sanogueralorenzo.sample.presentation.search

import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@AssistedModule
@InstallIn(ActivityComponent::class)
abstract class GifsModule {
    @Binds
    abstract fun provideViewModel(viewModel: GifsViewModel): ViewModel

    @Binds
    abstract fun bind_com_sanogueralorenzo_sample_presentation_search_GifsViewModel(
        factory: GifsViewModel_AssistedFactory
    ): GifsViewModel.Factory?
}
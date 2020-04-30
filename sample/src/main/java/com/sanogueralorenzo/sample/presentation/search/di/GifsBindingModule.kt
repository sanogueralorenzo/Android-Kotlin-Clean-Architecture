package com.sanogueralorenzo.sample.presentation.search.di

import androidx.lifecycle.ViewModel
import com.sanogueralorenzo.sample.di.ActivityScope
import com.sanogueralorenzo.sample.presentation.search.GifsFragment
import com.sanogueralorenzo.sample.presentation.search.GifsViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GifsBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [GifsModule::class]
    )
    abstract fun bindFragment(): GifsFragment
}

@AssistedModule
@Module(includes = [AssistedInject_GifsModule::class])
abstract class GifsModule {
    @Binds
    abstract fun provideViewModel(viewModel: GifsViewModel): ViewModel
}

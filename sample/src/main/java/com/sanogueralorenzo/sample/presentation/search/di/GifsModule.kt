package com.sanogueralorenzo.sample.presentation.search.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.migration.DisableInstallInCheck

@AssistedModule
@DisableInstallInCheck
@InstallIn(FragmentComponent::class)
@Module(includes = [AssistedInject_GifsModule::class])
interface GifsModule

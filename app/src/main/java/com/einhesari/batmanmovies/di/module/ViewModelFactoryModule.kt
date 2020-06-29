package com.einhesari.zomatosample.di.module

import androidx.lifecycle.ViewModelProvider
import com.einhesari.batmanmovies.ViewModelProviderFactory
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
     abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}
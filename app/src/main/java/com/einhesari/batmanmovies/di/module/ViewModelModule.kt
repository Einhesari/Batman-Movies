package com.einhesari.batmanmovies.di.module

import androidx.lifecycle.ViewModel
import com.einhesari.batmanmovies.di.scope.ViewModelKey
import com.einhesari.batmanmovies.presentation.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel
}
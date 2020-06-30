package com.einhesari.batmanmovies.di.module

import com.einhesari.batmanmovies.data.repository.MovieRepositoryImpl
import com.einhesari.batmanmovies.domain.usecase.MovieUseCase
import dagger.Module
import dagger.Provides

@Module
class MoviesUsecaseModule {
    @Provides
    fun provideMoviesUseCase(repository: MovieRepositoryImpl): MovieUseCase {
        return MovieUseCase(repository)
    }
}
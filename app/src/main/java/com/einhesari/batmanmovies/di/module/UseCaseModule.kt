package com.einhesari.batmanmovies.di.module

import com.einhesari.batmanmovies.data.repository.MovieRepositoryImpl
import com.einhesari.batmanmovies.domain.usecase.AllMoviesUseCase
import com.einhesari.batmanmovies.domain.usecase.SingleMovieUseCase
import dagger.Module
import dagger.Provides

@Module
class MoviesUsecaseModule {
    @Provides
    fun provideAllMoviesUseCase(repository: MovieRepositoryImpl): AllMoviesUseCase {
        return AllMoviesUseCase(repository)
    }

    @Provides
    fun provideSingleMovieUseCase(repository: MovieRepositoryImpl): SingleMovieUseCase {
        return SingleMovieUseCase(repository)
    }
}
package com.einhesari.batmanmovies.di.module

import com.einhesari.batmanmovies.data.repository.MovieRepositoryImpl
import com.einhesari.batmanmovies.domain.usecase.MovieUseCase
import com.einhesari.batmanmovies.domain.usecase.MoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class MoviesUsecaseModule {
    @Provides
    fun provideMoviesUseCase(repository: MovieRepositoryImpl): MoviesUseCase {
        return MoviesUseCase(repository)
    }

    @Provides
    fun provideMovieUseCase(repository: MovieRepositoryImpl): MovieUseCase {
        return MovieUseCase(repository)
    }
}
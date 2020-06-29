package com.einhesari.batmanmovies.di.module

import com.einhesari.batmanmovies.data.repository.MovieRepositoryImpl
import com.einhesari.batmanmovies.datasource.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class MovieRepositoryModule {

    @Provides
    fun provideMovieRepo(moviesDataSource: MovieRemoteDataSourceImpl): MovieRepositoryImpl {
        return MovieRepositoryImpl(moviesDataSource)
    }
}
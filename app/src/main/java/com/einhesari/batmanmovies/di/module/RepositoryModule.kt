package com.einhesari.batmanmovies.di.module

import com.einhesari.batmanmovies.data.repository.MovieRepositoryImpl
import com.einhesari.batmanmovies.datasource.MovieRemoteDataSourceImpl
import com.einhesari.batmanmovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class MovieRepositoryModule {

    @Provides
    fun provideMovieRepo(moviesDataSource: MovieRemoteDataSourceImpl): MovieRepository {
        return MovieRepositoryImpl(moviesDataSource)
    }
}
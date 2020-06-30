package com.einhesari.batmanmovies.di.module

import com.einhesari.batmanmovies.data.datasource.api.ApiService
import com.einhesari.batmanmovies.datasource.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class MoviesRemoteDataSourceModule {

    @Provides
    fun provideMoviesDataSource(apiService: ApiService): MovieRemoteDataSourceImpl {
        return MovieRemoteDataSourceImpl(apiService)
    }
}
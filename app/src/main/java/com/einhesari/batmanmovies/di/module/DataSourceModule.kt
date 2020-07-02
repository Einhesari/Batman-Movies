package com.einhesari.batmanmovies.di.module

import android.content.Context
import androidx.room.Room
import com.einhesari.batmanmovies.data.datasource.api.ApiService
import com.einhesari.batmanmovies.data.datasource.database.MovieCacheDataSource
import com.einhesari.batmanmovies.datasource.AppDataBase
import com.einhesari.batmanmovies.datasource.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideMoviesRemoteDataSource(apiService: ApiService): MovieRemoteDataSourceImpl {
        return MovieRemoteDataSourceImpl(apiService)
    }

    @Provides
    fun provideMoviesCacheDataSource(context: Context): MovieCacheDataSource {
        val db = Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            AppDataBase.DB_NAME
        ).build()
        return db.movieDao()
    }

}
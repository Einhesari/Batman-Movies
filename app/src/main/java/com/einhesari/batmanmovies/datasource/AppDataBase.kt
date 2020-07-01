package com.einhesari.batmanmovies.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.einhesari.batmanmovies.data.datasource.database.MovieCacheDataSource
import com.einhesari.batmanmovies.data.model.database.MovieEntity


@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieCacheDataSource

    companion object {
        const val DB_NAME = "batman-movie-database"

    }
}
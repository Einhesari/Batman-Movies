package com.einhesari.batmanmovies.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.einhesari.batmanmovies.data.model.database.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieCacheDataSource {

    @Query("SELECT * FROM batman_movies")
    fun getAllBatmanMovies(): Single<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun setAllBatmanMovies(movies: List<MovieEntity>): Completable

}
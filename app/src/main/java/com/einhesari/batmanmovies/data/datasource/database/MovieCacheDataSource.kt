package com.einhesari.batmanmovies.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.einhesari.batmanmovies.data.model.database.DetailedMovieEntity
import com.einhesari.batmanmovies.data.model.database.MovieEntity
import com.einhesari.batmanmovies.presentation.model.DetailedMovie
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieCacheDataSource {

    @Query("SELECT * FROM batman_movies")
    fun getAllBatmanMovies(): Single<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAllBatmanMovies(movies: List<MovieEntity>): Completable

    @Query("SELECT * FROM detailed_movies WHERE imdbID LIKE :imdbID LIMIT 1")
    fun getDetailedMovie(imdbID: String): Single<List<DetailedMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setDetailedMovie(movie: DetailedMovieEntity): Completable
}
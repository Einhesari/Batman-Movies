package com.einhesari.batmanmovies.datasource.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.einhesari.batmanmovies.data.datasource.database.MovieCacheDataSource
import com.einhesari.batmanmovies.data.model.database.DetailedMovieEntity
import com.einhesari.batmanmovies.data.model.database.MovieEntity
import com.einhesari.batmanmovies.datasource.AppDataBase
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDataBaseTest {

    private val movie1 = MovieEntity(
        imdbID = "tt0372784",
        title = "Movie1",
        year = "2005",
        type = "movie",
        poster = "https://m.media-amazon.com/images/M/MV5BZmUwNGU2ZmItMmRiNC00MjhlLTg5YWUtODMyNzkxODYzMmZlXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"
    )
    private val movie2 = MovieEntity(
        imdbID = "tt2975590",
        title = "Movie2",
        year = "2015",
        type = "movie",
        poster = "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
    )

    private val detailedMovie = DetailedMovieEntity(
        imdbID = "tt2975590",
        title = "detailedMovie",
        year = "2015",
        type = "movie",
        poster = "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
        rated = "PG-13",
        released = "15 Jun 2015",
        genre = "Action, Adventure",
        director = "Christopher Nolan",
        imdbRating = "8.2",
        imdbVotes = "1,267,031"
    )
    private lateinit var dao: MovieCacheDataSource
    private lateinit var db: AppDataBase
    private val compositeDisposable = CompositeDisposable()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDataBase::class.java
        ).build()
        dao = db.movieDao()
    }

    @Test
    fun dbReadAndWriteAllTest() {
        val movies = listOf(movie1, movie2)
        val setAllObserver = dao.setAllBatmanMovies(movies).test()
        compositeDisposable.add(setAllObserver)
        setAllObserver.assertComplete()
        setAllObserver.assertNoErrors()

        val getAllObserver = dao.getAllBatmanMovies().test()
        compositeDisposable.add(getAllObserver)
        getAllObserver.assertComplete()
        getAllObserver.assertNoErrors()
        getAllObserver.assertValue {
            it.isNotEmpty() &&
                    it.size == 2 &&
                    it.contains(movie1) &&
                    it.contains(movie2)
        }
    }

    @Test
    fun dbReadAndWriteDetailedMovieTest() {
        val setDetailedMovieObserver = dao.setDetailedMovie(detailedMovie).test()
        compositeDisposable.add(setDetailedMovieObserver)
        setDetailedMovieObserver.assertComplete()
        setDetailedMovieObserver.assertNoErrors()

        val getDetailedMovieObserver = dao.getDetailedMovie(detailedMovie.imdbID).test()
        compositeDisposable.add(getDetailedMovieObserver)
        getDetailedMovieObserver.assertNoErrors()
        getDetailedMovieObserver.assertComplete()
        getDetailedMovieObserver.assertValue {
            it.title.equals(detailedMovie.title)
        }

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
        compositeDisposable.dispose()
    }
}
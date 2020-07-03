package com.einhesari.batmanmovies.datasource.remote

import com.einhesari.batmanmovies.data.datasource.api.ApiService
import com.einhesari.batmanmovies.data.datasource.remote.MovieRemoteDataSource
import com.einhesari.batmanmovies.data.model.remote.FoundedMovie
import com.einhesari.batmanmovies.data.model.remote.SearchResponse
import com.einhesari.batmanmovies.data.model.remote.SingleMovieResponse
import com.einhesari.batmanmovies.datasource.MovieRemoteDataSourceImpl
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RemoteDataSourceTest {
    private val movie1 = FoundedMovie(
        imdbID = "tt0372784",
        title = "Movie1",
        year = "2005",
        type = "movie",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BZmUwNGU2ZmItMmRiNC00MjhlLTg5YWUtODMyNzkxODYzMmZlXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"
    )
    private val movie2 = FoundedMovie(
        imdbID = "tt2975590",
        title = "Movie2",
        year = "2015",
        type = "movie",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
    )
    private val detailedMovie = SingleMovieResponse(
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
        imdbVotes = "1,267,031",
        runtime = "",
        writer = "",
        actors = "",
        plot = "",
        language = "",
        awards = "",
        ratings = listOf(),
        metascore = "",
        dvdRealeseDate = "",
        boxOffice = "",
        website = "",
        production = "",
        response = ""
    )
    private val compositeDisposable = CompositeDisposable()

    @Mock
    private lateinit var apiService: ApiService

    private val movies = listOf(movie1, movie2)

    private val searchResponse = SearchResponse(
        foundedMovies = movies,
        totalResults = "2",
        Response = "True"
    )
    private lateinit var remoteDataSource: MovieRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }

        Mockito.`when`(apiService.getAllBatmanMovies(anyString()))
            .thenReturn(Single.just(searchResponse))

        Mockito.`when`(apiService.getMovieDetail(anyString()))
            .thenReturn(Single.just(detailedMovie))
        remoteDataSource =
            MovieRemoteDataSourceImpl(
                apiService
            )
    }

    @Test
    fun getAllMoviesTest() {
        val allMoviesObserver = remoteDataSource.getAllBatmanMovies().test()
        compositeDisposable.add(allMoviesObserver)
        allMoviesObserver.assertComplete()
        allMoviesObserver.assertNoErrors()
        allMoviesObserver.assertValue {
            it.foundedMovies.isNotEmpty() &&
                    it.foundedMovies.size == 2 &&
                    it.foundedMovies.containsAll(movies)
        }
    }

    @Test
    fun getDetailedMovie() {
        val detailedMovieObserver = remoteDataSource.getMovie("").test()
        compositeDisposable.add(detailedMovieObserver)
        detailedMovieObserver.assertComplete()
        detailedMovieObserver.assertNoErrors()
        detailedMovieObserver.assertValue {
            it.title.equals(detailedMovie.title)
        }
    }

    @After
    fun tearDown() {
        compositeDisposable.dispose()
    }

}
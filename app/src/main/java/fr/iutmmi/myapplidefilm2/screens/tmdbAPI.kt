package fr.iutmmi.myapplidefilm2.network

import fr.iutmmi.myapplidefilm2.screens.ActorResponse
import fr.iutmmi.myapplidefilm2.screens.MovieDetailResponse
import fr.iutmmi.myapplidefilm2.screens.MovieResponse
import fr.iutmmi.myapplidefilm2.screens.SeriesDetailResponse
import fr.iutmmi.myapplidefilm2.screens.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse


    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse


    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): SeriesResponse


    @GET("search/tv")
    suspend fun searchSeries(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): SeriesResponse


    @GET("person/popular")
    suspend fun getPopularActors(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): ActorResponse


    @GET("search/person")
    suspend fun searchActors(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): ActorResponse


    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): MovieDetailResponse

    @GET("tv/{tv_id}")
    suspend fun getSeriesDetail(
        @Path("tv_id") serieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): SeriesDetailResponse
}

package fr.iutmmi.myapplidefilm2.viewmodel1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iutmmi.myapplidefilm2.network.RetrofitClient
import fr.iutmmi.myapplidefilm2.screens.Actor
import fr.iutmmi.myapplidefilm2.screens.Movie
import fr.iutmmi.myapplidefilm2.screens.MovieDetailResponse
import fr.iutmmi.myapplidefilm2.screens.SeriesDetailResponse
import fr.iutmmi.myapplidefilm2.screens.Series
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _series = MutableStateFlow<List<Series>>(emptyList())
    val series: StateFlow<List<Series>> = _series

    private val _actors = MutableStateFlow<List<Actor>>(listOf())
    val actors: StateFlow<List<Actor>> = _actors

    private val _movieDetail = MutableStateFlow<MovieDetailResponse?>(null)
    val movieDetail: StateFlow<MovieDetailResponse?> = _movieDetail

    private val _seriesDetail = MutableStateFlow<SeriesDetailResponse?>(null)
    val seriesDetail: StateFlow<SeriesDetailResponse?> = _seriesDetail


    private val apiKey = "891b3b9153a6c661431b52b2e155451c"


    fun getFilmsInitiaux() {

        viewModelScope.launch {
            try {

                val response = RetrofitClient.api.getPopularMovies(apiKey)


                _movies.value = response.results
            } catch (e: Exception) {

                _movies.value = emptyList()
            }
        }
    }


    fun getSeriesPopulaires() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getPopularSeries(apiKey)
                _series.value = response.results
            } catch (e: Exception) {
                _series.value = emptyList()
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.searchMovies(apiKey, query)
                _movies.value = response.results
            } catch (e: Exception) {
                _movies.value = emptyList()
            }
        }
    }


    fun searchSeries(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.searchSeries(apiKey, query)
                _series.value = response.results
            } catch (e: Exception) {
                _series.value = emptyList()
            }
        }
    }



    fun getPopularActors() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getPopularActors(apiKey)
                _actors.value = response.results
            } catch (e: Exception) {
                _actors.value = emptyList()
            }
        }
    }


    fun searchActors(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.searchActors(apiKey, query)
                _actors.value = response.results
            } catch (e: Exception) {

            }
        }
    }


    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getMovieDetail(movieId, apiKey)
                _movieDetail.value = response
            } catch (e: Exception) {
                _movieDetail.value = null
            }
        }
    }

    fun getSeriesDetail(seriesId: Int) {
        viewModelScope.launch {
            try {

                val response = RetrofitClient.api.getSeriesDetail(seriesId, apiKey)
                _seriesDetail.value = response
            } catch (e: Exception) {
                _seriesDetail.value = null

            }
        }
    }

}
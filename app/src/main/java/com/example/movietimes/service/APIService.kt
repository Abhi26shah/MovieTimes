package com.example.movietimes.service

import com.example.movietimes.BuildConfig
import com.example.movietimes.constants.ApiConstants
import com.example.movietimes.dao.GenresResponseModel
import com.example.movietimes.dao.PopularMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface APIService {

    @GET("${ApiConstants.DISCOVER_ENDPOINT}/${ApiConstants.MOVIE_ENDPOINT}")
    fun getMovieList(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @QueryMap query: HashMap<String,Any>
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.DISCOVER_ENDPOINT}/${ApiConstants.TV_ENDPOINT}")
    fun getSeriesList(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @QueryMap query: HashMap<String,Any>
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.MOVIE_ENDPOINT}/${ApiConstants.TRENDING_MOVIE_ENDPOINT}")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.MOVIE_ENDPOINT}/${ApiConstants.UPCOMING_MOVIE_ENDPOINT}")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.MOVIE_ENDPOINT}/${ApiConstants.NOW_PLAYING_MOVIE_ENDPOINT}")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.MOVIE_ENDPOINT}/${ApiConstants.TOP_RATED_MOVIE_ENDPOINT}")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.TV_ENDPOINT}/${ApiConstants.TRENDING_MOVIE_ENDPOINT}")
    fun getPopularSeries(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.TV_ENDPOINT}/${ApiConstants.UPCOMING_TV_ENDPOINT}")
    fun getUpcomingSeries(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.TV_ENDPOINT}/${ApiConstants.NOW_PLAYING_TV_ENDPOINT}")
    fun getNowPlayingSeries(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.TV_ENDPOINT}/${ApiConstants.TOP_RATED_MOVIE_ENDPOINT}")
    fun getTopRatedSeries(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<PopularMoviesResponse>

    @GET("${ApiConstants.GENRE_ENDPOINT}/${ApiConstants.MOVIE_ENDPOINT}/${ApiConstants.LIST_ENDPOINT}")
    fun getGenresList(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<GenresResponseModel>

    @GET("${ApiConstants.GENRE_ENDPOINT}/${ApiConstants.TV_ENDPOINT}/${ApiConstants.LIST_ENDPOINT}")
    fun getTVGenresList(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Observable<GenresResponseModel>

    @GET("${ApiConstants.SEARCH_ENDPOINT}/${ApiConstants.MULTI_ENDPOINT}")
    fun getSearchedList(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @QueryMap map: HashMap<String, Any>
    ): Observable<PopularMoviesResponse>

}
package com.example.movietimes.repository

import android.content.Context
import android.database.Observable
import com.example.movietimes.dao.GenresResponseModel
import com.example.movietimes.dao.PopularMoviesResponse
import com.example.movietimes.service.ResponseListener


/**
 * Created by Abhishek Shah on 12 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
interface MovieDBRepository {

    fun getMoviesList(
        context: Context,
        query: HashMap<String, Any>,
        callback: ResponseListener<PopularMoviesResponse>
    )

    fun getSeriesList(
        context: Context,
        query: HashMap<String, Any>,
        callback: ResponseListener<PopularMoviesResponse>
    )

    fun getMoviesDetails()

    fun getSeriesDetails()

    fun getVideos()

    fun getPopularMovies(context: Context, callback: ResponseListener<PopularMoviesResponse>)

    fun getUpcomingMovies(context: Context, callback: ResponseListener<PopularMoviesResponse>)

    fun getNowPlayingMovies(context: Context, callback: ResponseListener<PopularMoviesResponse>)

    fun getTopRatedMovies(context: Context, callback: ResponseListener<PopularMoviesResponse>)

    fun getPopularSeries(context: Context, callback: ResponseListener<PopularMoviesResponse>)

    fun getUpcomingSeries(context: Context, callback: ResponseListener<PopularMoviesResponse>)

    fun getNowPlayingSeries(context: Context, callback: ResponseListener<PopularMoviesResponse>)

    fun getTopRatedSeries(context: Context, callback: ResponseListener<PopularMoviesResponse>)

    fun getGenresList(context: Context, callback: ResponseListener<GenresResponseModel>)

    fun getTVGenresList(context: Context, callback: ResponseListener<GenresResponseModel>)

    fun getSearchedList(
        context: Context,
        query: HashMap<String, Any>,
        callback: ResponseListener<PopularMoviesResponse>
    )
}
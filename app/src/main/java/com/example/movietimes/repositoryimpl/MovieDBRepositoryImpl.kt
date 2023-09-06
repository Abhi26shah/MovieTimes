package com.example.movietimes.repositoryimpl

import android.content.Context
import com.example.movietimes.dao.GenresResponseModel
import com.example.movietimes.dao.PopularMoviesResponse
import com.example.movietimes.manager.NetworkManager
import com.example.movietimes.repository.MovieDBRepository
import com.example.movietimes.retrofit.APIUtils
import com.example.movietimes.service.ResponseListener


/**
 * Created by Abhishek Shah on 12 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
class MovieDBRepositoryImpl : MovieDBRepository {

    companion object {
        val networkManager = NetworkManager()
    }

    override fun getMoviesList(
        context: Context,
        query: HashMap<String, Any>,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getMovieList(query = query),
            callBack = callback
        )
    }

    override fun getSeriesList(
        context: Context,
        query: HashMap<String, Any>,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getSeriesList(query = query),
            callBack = callback
        )
    }

    override fun getMoviesDetails() {
//        TODO("Not yet implemented")
    }

    override fun getSeriesDetails() {
//        TODO("Not yet implemented")
    }

    override fun getVideos() {
//        TODO("Not yet implemented")
    }

    override fun getPopularMovies(
        context: Context,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Get The list for Most Popular Movies")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getPopularMovies(), callBack = callback
        )
    }

    override fun getUpcomingMovies(
        context: Context,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Get The list for Most Upcoming Movies")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getUpcomingMovies(), callBack = callback
        )
    }

    override fun getNowPlayingMovies(
        context: Context,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getNowPlayingMovies(), callBack = callback
        )
    }

    override fun getTopRatedMovies(
        context: Context,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getTopRatedMovies(), callBack = callback
        )
    }

    override fun getPopularSeries(
        context: Context,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getPopularSeries(), callBack = callback
        )
    }

    override fun getUpcomingSeries(
        context: Context,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getUpcomingSeries(), callBack = callback
        )
    }

    override fun getNowPlayingSeries(
        context: Context,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getNowPlayingSeries(), callBack = callback
        )
    }

    override fun getTopRatedSeries(
        context: Context,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getTopRatedSeries(), callBack = callback
        )
    }

    override fun getGenresList(context: Context, callback: ResponseListener<GenresResponseModel>) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getGenresList(), callBack = callback
        )
    }

    override fun getTVGenresList(
        context: Context,
        callback: ResponseListener<GenresResponseModel>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getTVGenresList(), callBack = callback
        )
    }

    override fun getSearchedList(
        context: Context,
        query: HashMap<String, Any>,
        callback: ResponseListener<PopularMoviesResponse>
    ) {
//        TODO("Not yet implemented")
        networkManager.createAPIRequest(
            APIUtils.getApiConnectivityService(context).getSearchedList(map = query),
            callBack = callback
        )
    }
}
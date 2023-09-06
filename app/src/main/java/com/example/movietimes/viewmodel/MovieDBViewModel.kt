package com.example.movietimes.viewmodel

import android.content.Context
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import com.example.movietimes.dao.Genres
import com.example.movietimes.dao.GenresResponseModel
import com.example.movietimes.dao.PopularMovies
import com.example.movietimes.dao.PopularMoviesResponse
import com.example.movietimes.manager.ErrorModel
import com.example.movietimes.repositoryimpl.MovieDBRepositoryImpl
import com.example.movietimes.service.ResponseListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * Created by Abhishek Shah on 19 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
class MovieDBViewModel: ViewModel() {

    private val movieRepository = MovieDBRepositoryImpl()

    private val _popularMoviesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val popularMoviesList: StateFlow<ArrayList<PopularMovies>> = _popularMoviesList

    private val _upcomingMoviesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val upcomingMoviesList: StateFlow<ArrayList<PopularMovies>> = _upcomingMoviesList

    private val _nowPlayingMoviesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val nowPlayingMoviesList: StateFlow<ArrayList<PopularMovies>> = _nowPlayingMoviesList

    private val _topRatedMoviesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val topRatedMoviesList: StateFlow<ArrayList<PopularMovies>> = _topRatedMoviesList

    private val _popularSeriesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val popularSeriesList: StateFlow<ArrayList<PopularMovies>> = _popularSeriesList

    private val _upcomingSeriesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val upcomingSeriesList: StateFlow<ArrayList<PopularMovies>> = _upcomingSeriesList

    private val _nowPlayingSeriesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val nowPlayingSeriesList: StateFlow<ArrayList<PopularMovies>> = _nowPlayingSeriesList

    private val _topRatedSeriesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val topRatedSeriesList: StateFlow<ArrayList<PopularMovies>> = _topRatedSeriesList

    private val _genresList = MutableStateFlow(arrayListOf<Genres>())
    val genresList: StateFlow<ArrayList<Genres>> = _genresList

    private val _genreWithMovieList = SnapshotStateMap<Genres,ArrayList<PopularMovies>>()
    val genreWithMovies: MutableMap<Genres,ArrayList<PopularMovies>> = _genreWithMovieList

    private val _tvGenresList = MutableStateFlow(arrayListOf<Genres>())
    val tvGenresList: StateFlow<ArrayList<Genres>> = _tvGenresList

    private val _genreWithSeriesList = SnapshotStateMap<Genres,ArrayList<PopularMovies>>()
    val genreWithSeries: MutableMap<Genres,ArrayList<PopularMovies>> = _genreWithSeriesList

    private val _searchedMoviesList = MutableStateFlow(arrayListOf<PopularMovies>())
    val searchedMoviesList: StateFlow<ArrayList<PopularMovies>> = _searchedMoviesList

    fun getPopularMovies(context: Context) {
        movieRepository.getPopularMovies(context = context,
            object : ResponseListener<PopularMoviesResponse> {
            override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                TODO("On Successful Response")
                _popularMoviesList.value = response.popularMovies
            }

            override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                TODO("On Error Received")
            }

            override fun onLoading(isLoading: Boolean) {
//                TODO("While Api requesting to server")
            }
        })
    }

    fun getUpcomingMovies(context: Context) {
        movieRepository.getUpcomingMovies(context,
            object : ResponseListener<PopularMoviesResponse> {
                override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _upcomingMoviesList.value = response.popularMovies
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    fun getNowPlayingMovies(context: Context) {
        movieRepository.getNowPlayingMovies(context,
            object : ResponseListener<PopularMoviesResponse> {
                override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _nowPlayingMoviesList.value = response.popularMovies
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    fun getTopRatedMovies(context: Context) {
        movieRepository.getTopRatedMovies(context,
            object : ResponseListener<PopularMoviesResponse> {
                override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _topRatedMoviesList.value = response.popularMovies
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    fun getPopularSeries(context: Context) {
        movieRepository.getPopularSeries(context,
            object : ResponseListener<PopularMoviesResponse> {
                override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _popularSeriesList.value = response.popularMovies
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    fun getUpcomingSeries(context: Context) {
        movieRepository.getUpcomingSeries(context,
            object : ResponseListener<PopularMoviesResponse> {
                override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _upcomingSeriesList.value = response.popularMovies
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    fun getNowPlayingSeries(context: Context) {
        movieRepository.getNowPlayingSeries(context,
            object : ResponseListener<PopularMoviesResponse> {
                override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _nowPlayingSeriesList.value = response.popularMovies
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    fun getTopRatedSeries(context: Context) {
        movieRepository.getTopRatedSeries(context,
            object : ResponseListener<PopularMoviesResponse> {
                override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _topRatedSeriesList.value = response.popularMovies
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    fun getGenresList(context: Context) {
        movieRepository.getGenresList(context,
            object : ResponseListener<GenresResponseModel> {
                override fun onResponseReceived(response: GenresResponseModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _genresList.value = response.genres
                    getMoviesForAllGenres(context = context)
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    fun getTVGenresList(context: Context) {
        movieRepository.getTVGenresList(context,
            object : ResponseListener<GenresResponseModel> {
                override fun onResponseReceived(response: GenresResponseModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _tvGenresList.value = response.genres
                    getSeriesForAllGenres(context = context)
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }

            })
    }

    private fun getMoviesForAllGenres(context: Context) {
        genresList.value.forEach {
            getMoviesForGenre(context,it)
        }
    }

    private fun getSeriesForAllGenres(context: Context) {
        tvGenresList.value.forEach {
            getSeriesForGenre(context,it)
        }
    }

    private fun getMoviesForGenre(context: Context, genre: Genres, query:HashMap<String,Any> = HashMap()) {
        query["with_genres"] = genre.id
        movieRepository.getMoviesList(context = context, query = query, object : ResponseListener<PopularMoviesResponse> {
            override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                TODO("Not yet implemented")
                _genreWithMovieList[genre] = response.popularMovies
            }

            override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                TODO("Not yet implemented")
            }

            override fun onLoading(isLoading: Boolean) {
//                TODO("Not yet implemented")
            }

        })
    }

    private fun getSeriesForGenre(context: Context, genre: Genres, query:HashMap<String,Any> = HashMap()) {
        query["with_genres"] = genre.id
        movieRepository.getSeriesList(context = context, query = query, object : ResponseListener<PopularMoviesResponse> {
            override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                TODO("Not yet implemented")
                _genreWithSeriesList[genre] = response.popularMovies
            }

            override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                TODO("Not yet implemented")
            }

            override fun onLoading(isLoading: Boolean) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun getSearchedList(context: Context, query: String) {
        val map = HashMap<String, Any>()
        map["query"] = query
        movieRepository.getSearchedList(context, map,
            object : ResponseListener<PopularMoviesResponse> {
                override fun onResponseReceived(response: PopularMoviesResponse, requestCode: Int) {
//                    TODO("Not yet implemented")
                    _searchedMoviesList.value = response.popularMovies
                }

                override fun onErrorReceived(error: ErrorModel, requestCode: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onLoading(isLoading: Boolean) {
//                    TODO("Not yet implemented")
                }
            })
    }
}
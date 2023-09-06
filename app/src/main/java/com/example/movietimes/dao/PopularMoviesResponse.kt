package com.example.movietimes.dao

import com.example.movietimes.manager.ErrorModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Abhishek Shah on 19 June 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
class PopularMoviesResponse: Serializable {

    @SerializedName("error")
    var error: ErrorModel? = null

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("results")
    var popularMovies: ArrayList<PopularMovies> = arrayListOf()

    @SerializedName("total_pages")
    var totalPages: Long = 0

    @SerializedName("total_results")
    var totalResults: Long = 0

    override fun toString(): String {
        return "PopularMoviesResponse(page=$page, popularMovies=$popularMovies, totalPages=$totalPages, totalResults=$totalResults)"
    }
}

class PopularMovies: Serializable {

    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("backdrop_path")
    var backdropPath: String = ""

    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int> = arrayListOf()

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("original_language")
    var originalLanguage: String = ""

    @SerializedName("original_title")
    var originalTitle: String = ""

    @SerializedName("overview")
    var overview: String = ""

    @SerializedName("popularity")
    var popularity: Float = 0f

    @SerializedName("poster_path")
    var posterPath: String? = ""

    @SerializedName("release_date")
    var releaseDate: String = ""

    @SerializedName("title")
    var title: String = ""

    @SerializedName("video")
    var video: Boolean = false

    @SerializedName("vote_average")
    var voteAverage: Float = 0f

    @SerializedName("vote_count")
    var voteCount: Long = 0
}
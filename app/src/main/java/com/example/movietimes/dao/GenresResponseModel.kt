package com.example.movietimes.dao

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Abhishek Shah on 27 July 2023
 *
 * Thinkitive Technologies Pvt. Ltd.
 */
class GenresResponseModel: Serializable {
    @SerializedName("genres")
    val genres: ArrayList<Genres> = arrayListOf()
}

class Genres: Serializable {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name = ""
}